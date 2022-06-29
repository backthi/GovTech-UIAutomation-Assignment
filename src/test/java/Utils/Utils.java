package Utils;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import tests.BaseClass;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static tests.BaseClass.ROOTPATH;

public class Utils {
    public int INDENTATION = 4;
    public WebDriverWait wait;
    File JSONFile;
    public static String Locators_JSON_Path;
    public static String TestData_JSON_Path;
    public final Logger logger = LoggerFactory.getLogger(Utils.class);
    public By By;
    public By element;

    //*******************************
    /**
     * extractingJSONStringFromJSONFile - Function to Extract String specific value from JSON Name
     * @param - JSON_FileName
     * @return JSON Object
     */
    public JSONObject extractingJSONStringFromJSONFile(String JSON_FileName)
    {
        String ExtractedJSONString = "";
        JSONObject jsonObj = null;
        String Indent_json_string = "";
        JSONObject Final_jsonObject = null;
        Locators_JSON_Path = ROOTPATH + "\\src\\test\\java\\locators\\";
        TestData_JSON_Path = ROOTPATH + "\\src\\test\\resources\\TestData\\";
        if(System.getProperty("os.name").toLowerCase().contains("mac") || System.getProperty("os.name").toLowerCase().contains("linux"))
        {
            Locators_JSON_Path = Locators_JSON_Path.replace("\\", "/");
            TestData_JSON_Path = TestData_JSON_Path.replace("\\", "/");
        }
        try {
            if (JSON_FileName.toUpperCase().contains("TD_"))
            {
                JSONFile = new File(TestData_JSON_Path + JSON_FileName+ ".JSON" );
            }
            else
            {
                JSONFile = new File(Locators_JSON_Path + JSON_FileName+ ".JSON" );
            }

            Reader fileReader = new FileReader(JSONFile);
            BufferedReader bufReader = new BufferedReader(fileReader);
            StringBuilder sb = new StringBuilder();
            String line = bufReader.readLine();
            while (line != null) {
                sb.append(line).append("\n");
                line = bufReader.readLine();
                ExtractedJSONString = sb.toString();
            }
            jsonObj = new JSONObject(ExtractedJSONString);
            Indent_json_string = jsonObj.toString(INDENTATION);
            Final_jsonObject = new JSONObject(Indent_json_string);
            return Final_jsonObject;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    //*******************************
    /**
     * extractValueFromJSONName - Function to Extract specific value from JSON Name
     * @param - json_obj, JSON_Key
     * @return JSON Object of the specific JSON Key
     */
    public Object extractValueFromJSONName(JSONObject json_obj, String key) throws JSONException {
        Object finalresult = null;
        try {
            JSONArray keys = json_obj.names();
            for (int i = 0; i <= keys.length(); i++) {
                if (finalresult != null) {
                    return finalresult;
                }

                String current_key = keys.get(i).toString();

                if (current_key.equals(key)) {
                    finalresult = json_obj.get(current_key);
                    return finalresult;
                }

                if (json_obj.get(current_key).getClass().getName().equals("org.json.JSONObject")) {
                    extractValueFromJSONName((JSONObject) json_obj.get(current_key), key);
                } else if (json_obj.get(current_key).getClass().getName().equals("org.json.JSONArray")) {
                    for (int j = 0; j < ((JSONArray) json_obj.get(current_key)).length(); j++) {
                        if (((JSONArray) json_obj.get(current_key)).get(j).getClass().getName().equals("org.json.JSONObject")) {
                            extractValueFromJSONName((JSONObject) ((JSONArray) json_obj.get(current_key)).get(j), key);
                        }
                    }
                }
            }
            return null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    //*******************************
    /**
     * extractValueFromJSONFile - Function to Extract specific value from JSON Key
     * @param - JSON_FileName, JSON_Key
     * @return JSON String of the specific JSON Key
     */
    public String extractValueFromJSONFile(String JSON_FileName, String key)
    {
        JSONObject json_obj = null;
        Object JSONValue_obj = null;
        String str_Value = "";
        json_obj = extractingJSONStringFromJSONFile(JSON_FileName);
        JSONValue_obj = extractValueFromJSONName(json_obj,key);
        str_Value = JSONValue_obj.toString();
        return str_Value;
    }

    //*******************************
    /**
     * waitForVisibility - Function to wait for an Element
     * @param - by element, time duration to wait
     * @return nothing
     */
    public void waitForVisibility(By by, int seconds)
    {
        try
        {
            wait = new WebDriverWait(BaseClass.getDriver(), seconds);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            Assert.assertNotNull(BaseClass.getDriver().findElement(by));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
    //*******************************
    /**
     * clickElement - Function to click an Element
     * @param - element, time duration to wait
     * @return true or false
     */
    public boolean clickElement(By element,int seconds) {
        try
        {
            waitForVisibility(element,seconds);
            BaseClass.getDriver().findElement(element).click();
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            logger.error("clickElement","Failed to click an element: " + element );
            return false;
        }
    }

    //*******************************
    /**
     * submitElement - Function to click an Element which has submit type
     * @param - element, time duration to wait
     * @return true or false
     */
    public boolean submitElement(By element ,int seconds) {
        try
        {
            waitForVisibility(element,seconds);
            BaseClass.getDriver().findElement(element).submit();
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            logger.error("SubmitElement","Failed to submit an element: " + element );
            return false;
        }
    }
    //*******************************
    /**
     * getWebElement - Function to get an Element
     * @param - element, time duration to wait
     * @return WebElement
     */
    public WebElement getWebElement(By webElement ,int seconds)
    {
        WebElement element;
        try
        {
            waitForVisibility(webElement,seconds);
            element = BaseClass.getDriver().findElement(webElement);
            return element;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            logger.error("getWebElement","Failed to get an element: " + webElement );
            return null;
        }
    }
    //*******************************
    /**
     * typeTextToElement - Function to type the text into an Element
     * @param - element, ValueToType
     * @return true or false
     */
    public boolean typeTextToElement(By element,  String ValueToType)
    {
        try
        {
            waitForVisibility(element, 20);
            BaseClass.getDriver().findElement(element).click();
            BaseClass.getDriver().findElement(element).sendKeys(ValueToType);
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            logger.error("Failed to type text on an element: " + element );
            return false;
        }
    }
    //*******************************
    /**
     * getTextFromElement - Function to get text value of an Element
     * @param - element
     * @return string text value of an Element
     */
    public String getTextFromElement(By element)
    {
        String str_getText = "";
        try
        {
            waitForVisibility(element, 20);
            str_getText = BaseClass.getDriver().findElement(element).getText();
            return str_getText;
        }

        catch(Exception e)
        {
            e.printStackTrace();
            e.getMessage();
            logger.error("GetTextFromElement", "Failed to get text from an element: " + element);
            return null;
        }
    }

    //*******************************
    /**
     * getAttributeFromElement - Function to get attribute value of an Element
     * @param - element, attributeName
     * @return string attribute value of an Element
     */
    public String getAttributeFromElement(By element, String attributeName)
    {
        String str_getText = "";
        try
        {
            waitForVisibility(element, 20);
            str_getText = BaseClass.getDriver().findElement(element).getAttribute(attributeName);
            return str_getText;
        }

        catch(Exception e)
        {
            e.printStackTrace();
            e.getMessage();
            logger.error("GetTextFromElement", "Failed to get text from an element: " + element);
            return null;
        }
    }

    //*******************************
    /**
     * getFutureYearFromCurrent - Function to get future year from the current year
     * @param - number of year, date format
     * @return Future year in the specific Date format
     */
    public String getFutureYearFromCurrent(int year, String format)
    {
        String specificDate = "";
        Date date = new Date();
        try
        {
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            instance.add(Calendar.YEAR, + year);
            SimpleDateFormat isoFormat = new SimpleDateFormat(format);
            specificDate = isoFormat.format(instance.getTime());
            return specificDate;
        }

        catch(Exception e)
        {
            e.printStackTrace();
            e.getMessage();
            logger.error("GetTextFromElement", "Failed to get text from an element: " + element);
            return null;
        }
    }

    //*******************************
    /**
     * getPreviousYearFromCurrent - Function to get previous year from the current year
     * @param - number of year, date format
     * @return previous year in the specific Date format
     */
    public String getPreviousYearFromCurrent(int year, String format)
    {
        String specificDate = "";
        Date date = new Date();
        try
        {
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            instance.add(Calendar.YEAR, - year);
            SimpleDateFormat isoFormat = new SimpleDateFormat(format);
            specificDate = isoFormat.format(instance.getTime());
            return specificDate;
        }

        catch(Exception e)
        {
            e.printStackTrace();
            e.getMessage();
            logger.error("GetTextFromElement", "Failed to get text from an element: " + element);
            return null;
        }
    }

    //*******************************
    /**
     * getFutureMonthFromCurrent - Function to get future Month from the current month
     * @param - number of months, date format
     * @return Future Month in the specific Date format
     */
    public String getFutureMonthFromCurrent(int month, String format)
    {
        String specificDate = "";
        Date date = new Date();
        try
        {
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            instance.add(Calendar.MONTH, + month);
            SimpleDateFormat isoFormat = new SimpleDateFormat(format);
            specificDate = isoFormat.format(instance.getTime());
            return specificDate;
        }

        catch(Exception e)
        {
            e.printStackTrace();
            e.getMessage();
            logger.error("GetTextFromElement", "Failed to get text from an element: " + element);
            return null;
        }
    }


    //*******************************
    /**
     * getFutureDayFromCurrent - Function to get future Day from the today
     * @param - number of days, date format
     * @return Future Day in the specific Date format
     */
    public String getFutureDayFromCurrent(int days, String format)
    {
        String specificDate = "";
        Date date = new Date();
        try
        {
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            instance.add(Calendar.DAY_OF_MONTH, + days);
            SimpleDateFormat isoFormat = new SimpleDateFormat(format);
            specificDate = isoFormat.format(instance.getTime());
            return specificDate;
        }

        catch(Exception e)
        {
            e.printStackTrace();
            e.getMessage();
            logger.error("GetTextFromElement", "Failed to get text from an element: " + element);
            return null;
        }
    }

    //*******************************
    /**
     * getTestDataFromJSON - Function to get TD from JSON file
     * @param - JSONfileName, JSONKey
     * @return Test Data String
     */
    public String getTestDataFromJSON(String JSONfileName, String JSONKey)
    {
        String strTestData = "";
        try
        {
            strTestData = extractValueFromJSONFile(JSONfileName, JSONKey);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return strTestData;
    }

    //*******************************
    /**
     * getFrameSize - Function to get the size of the frame
     * @param - Nothing
     * @return integer
     */
    public int getFrameSize()
    {
        int size=0;
        try {
            size = BaseClass.getDriver().findElements(By.tagName("iframe")).size();
        }
        catch(Exception ex)
        {
            logger.error("getFrameSize", "Error in getting iframe details" + ex.getMessage());
        }

        return size;
    }
    //*******************************
    /**
     * switchToFrame - Function to switch from one to another
     * @param -frameID
     * @return nothing
     */
    public void switchToFrame(int frameID)
    {
        try {
            if(frameID<0)
                BaseClass.getDriver().switchTo().defaultContent();
            else
                BaseClass.getDriver().switchTo().frame(frameID);
        }
        catch(Exception ex)
        {
            logger.error("switchToFrame", "Error in switching to iframe" + ex.getMessage());
        }
    }
    //*******************************
    /**
     * readConfigFile - Function to reads Property file and returns properties
     * @param - nothing
     * @return properties from the Config file
     */
    public Properties readConfigFile()
    {
        File configFilePath;
        if(System.getProperty("os.name").toLowerCase().contains("mac") || System.getProperty("os.name").toLowerCase().contains("linux"))
        {
            configFilePath = new File("./src/test/resources/Config/Config.properties");
        }
        else
        {
            configFilePath = new File(".\\src\\test\\resources\\Config\\Config.properties");
        }
        Properties props = null;
        try {
            FileInputStream fileInput = new FileInputStream(configFilePath);
            props = new Properties();
            props.load(fileInput);
            fileInput.close();
        } catch (FileNotFoundException ex)
        {
            logger.error("File does not exists");
        } catch (IOException ex) {
            logger.error("IO Error");
        }
        return props;
    }
    //*******************************
    /**
     * isWebElementNotDisplayed - Function to verify the web element should not display status
     * @param -locator webelement to find
     * @return returns the webelement status
     */
    public boolean isWebElementNotDisplayed(By element, int seconds)
    {
        boolean flag = false;
        try
        {
            waitForVisibility(element,seconds);
            if (BaseClass.getDriver().findElement(element).isDisplayed())
            {
                logger.error("WebElement " + element + "was displayed");
                flag = false;
            }
            else {
                logger.info("WebElement " + element + "was not displayed");
                flag=true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }
    //*******************************
    /**
     * isWebElementDisplayed - Function to check the web element displayed or not
     * @param -locator webelement to find, Time duration to wait
     * @return true or false
     */
    public boolean isWebElementDisplayed(By element, int seconds)
    {
        boolean flag = false;
        try
        {
            waitForVisibility(element,seconds);
            if (BaseClass.getDriver().findElement(element).isDisplayed())
            {
                logger.info("WebElement " + element + "was displayed");
                flag = true;
            }
            else
            {
                logger.error("WebElement " + element + "was not displayed");
                flag = false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    //*******************************
    /**
     * isWebElementDisplayedByText - Function to check the web element displayed or not
     * @param -locator webelement to find, Time duration to wait
     * @return true or false
     */
    public boolean isWebElementDisplayedByText(String text, int seconds)
    {
        boolean flag = false;
        By by;
        try
        {
            by = By.xpath("//*[contains(text(),'"+ text +"')]");
            waitForVisibility(by,seconds);
            if (BaseClass.getDriver().findElement(by).isDisplayed())
            {
                logger.info("WebElement " + element + "was displayed");
                flag = true;
            }
            else
            {
                logger.error("WebElement " + element + "was not displayed");
                flag = false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

//*******************************
    /**
     * clearTextFromEdit - Function to check the web element displayed or not
     * @param -locator webelement to find, Time duration to wait
     * @return true or false
     */
    public void clearTextFromEdit(By element, int seconds)
    {
        try
        {
            waitForVisibility(element,seconds);
            BaseClass.getDriver().findElement(element).clear();
            logger.info("WebElement " + element + "was displayed");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //*******************************
    /**
     * verifyEmptyFiledAlertMessage - Function to check the web element displayed or not
     * @param -locator webelement to find, Time duration to wait
     * @return true or false
     */
    public boolean verifyEmptyFiledAlertMessage(By element, String valueToType)
    {
        boolean flag = false;
        Actions keys = new Actions(BaseClass.getDriver());
        try
        {
            Assert.assertTrue(typeTextToElement(element, valueToType));
            Thread.sleep(1000);
            keys.sendKeys(Keys.chord(Keys.SHIFT, Keys.CONTROL, Keys.LEFT, Keys.DELETE)).perform();
            Thread.sleep(2000);
            BaseClass.getDriver().findElement(element).sendKeys(Keys.TAB);
            Thread.sleep(2000);
            By warningMessageEmptyField_Text = By.xpath("//*[contains(text(), 'We need a response for this field')]");
            Assert.assertTrue(isWebElementDisplayed(warningMessageEmptyField_Text, 15));
            Thread.sleep(2000);
            flag = true;
        }
        catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    //*******************************
    /**
     * clearEntireText - Function to clearEntireText
     * @param -locator webelement to find, Time duration to wait
     * @return nothing
     */
    public void clearEntireText(By element)
    {
        try
        {
            Assert.assertTrue(clickElement(element, 10));
            Thread.sleep(2000);
            BaseClass.getDriver().findElement(element).sendKeys(Keys.CLEAR);
            Thread.sleep(2000);
            clearTextFromEdit(element, 10);
            Thread.sleep(1000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //*******************************
    /**
     * getWebElements - Function to get the list of elements with similar xpath
     * @param - element
     * @return list of elements
     */
    public List<WebElement> getWebElements(By element)
    {
        List<WebElement> webElements = new ArrayList<WebElement>();
        try {
            webElements = BaseClass.getDriver().findElements(element);
            return webElements;

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            logger.error("getWebElements", "unable to retrieve data");
        }
        return webElements;
    }

    //*******************************
    /**
     * getScreenShot - Function to capture the screenshot
     * @param - filename (for new file)
     * @return returns Screenshot Path
     */
    public String getScreenShot(String fileName) throws Exception
    {
        String folderName = "Failedscreenshots";
        File srcFile = null;
        String screenShotPath = null;
        if(BaseClass.getDriver()!=null)
        {
            TakesScreenshot scrShot =((TakesScreenshot)BaseClass.getDriver());
            srcFile = scrShot.getScreenshotAs(OutputType.FILE);
            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
            File dir = new File(System.getProperty("user.dir") + "/" + folderName);
            if (dir.exists()) {
                logger.info("Folder already exists");
                FileUtils.deleteDirectory(dir);
                new File(System.getProperty("user.dir") + "/" + folderName).mkdir();
            } else {
                logger.info("folder doesnot exist. Creating it");
                new File(System.getProperty("user.dir") + "/" + folderName).mkdir();
            }
            screenShotPath = System.getProperty("user.dir") + "/" + folderName + "/" + fileName + "_" + dateFormat.format(new Date()) + ".jpeg";
            FileUtils.copyFile(srcFile, new File(screenShotPath));
        }
        return screenShotPath;
    }

    //*******************************
    /**
     * isWebElementsTextDisplayed - Function to check if the list of web elements text verified successfully
     * @param - elementsTextArray
     * @return returns true or false
     */
    public boolean isWebElementsTextDisplayed(String[] elementsTextArray, int seconds)
    {
        boolean flag = false;
        try
        {
            for (int i= 0; i<elementsTextArray.length; i++) {
                if (BaseClass.getDriver().findElement(By.xpath("//*[contains(text(),'" + elementsTextArray[i] + "')]")).isDisplayed()) {
                    logger.info("WebElement " + elementsTextArray[i] + " was displayed");
                    flag = true;
                } else {
                    logger.error("WebElement " + elementsTextArray[i] + "was not displayed");
                    flag = false;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    //*******************************
    /**
     * verifyWebElementsFromTDArray - Function to verify if the list of web elements find successfully
     * @param - TD JSON File and TD JSON Key
     * @return returns true or false
     */
    public boolean verifyWebElementsFromTDArray(String[] TD_JSON_File, String[] TD_JSON_Key)
    {
        boolean flag = false;
        String TDValue = "";
        try
        {

            for (int i= 0; i<TD_JSON_File.length; i++)
            {
                for (int j= 0; j<TD_JSON_Key.length; j++)
                {
                    TDValue = getTestDataFromJSON(TD_JSON_File[i], TD_JSON_Key[j]);
                    if (BaseClass.getDriver().findElement(By.xpath("//*[contains(text()," + TDValue + ")]")).isDisplayed())
                    {
                        logger.info("WebElement " + TDValue + "was displayed");
                        flag = true;
                    }
                }
                if (flag = false)
                {
                    Assert.fail("WebElement " + TDValue + "was displayed");
                    logger.error("WebElement " + TDValue + "was not displayed");
                    break;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    //*******************************
    /**
     * verifyHashMapValue - Function to verify HashMap values
     * @param - Hashmap
     * @return true or false
     */
    public boolean verifyHashMapValue(Map<String,String> hashMap)
    {
        boolean flag = false;
        String TDValue = "";
        try
        {
          for (Map.Entry<String, String> entry : hashMap.entrySet())
            {
                TDValue = getTestDataFromJSON(entry.getValue(), entry.getKey());
                if (BaseClass.getDriver().findElement(By.xpath("//*[contains(text(),'" + TDValue + "')]")).isDisplayed())
                {
                    logger.info("WebElement " + TDValue + "was displayed");
                    flag = true;
                }
                else{
                    Assert.fail("WebElement " + TDValue + "was displayed");
                    logger.error("WebElement " + TDValue + "was not displayed");
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }
}
