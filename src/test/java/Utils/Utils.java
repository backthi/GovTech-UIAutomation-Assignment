package Utils;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.omg.CORBA.TIMEOUT;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import tests.BaseClass;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

import static tests.BaseClass.ROOTPATH;
import static tests.BaseClass.driver;

public class Utils {
    public int INDENTATION = 4;
    public WebDriverWait wait;
    File JSONFile;
    public static String Locators_JSON_Path;
    public static String TestData_JSON_Path;
    public final Logger logger = LoggerFactory.getLogger(Utils.class);
    String str_obj_Locators;
    public By By;
    public By element;
    Properties prop = readConfigFile();
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

    // To Extract specific value from JSON Key
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

    //Wait
    public void waitForVisibility(By by, int seconds)
    {
        try
        {
            wait = new WebDriverWait(driver, seconds);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            Assert.assertNotNull(driver.findElement(by));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
    //**********************************
    public By CreateLocatorObjFromLocatorType(String JSON_FileName, String objJSONKey)
    {
        try
        {
            String[] str_locator_arr = objJSONKey.split("#");
            str_obj_Locators = extractValueFromJSONFile(JSON_FileName, objJSONKey);
            if ("ID".equals(str_locator_arr[0].toUpperCase()))
            {
                By = By.id(str_obj_Locators);
                return By;
            }else if ("XPATH".equals(str_locator_arr[0].toUpperCase()))
            {
                By = By.xpath(str_obj_Locators);
                return By;
            }else if("NAME".equals(str_locator_arr[0].toUpperCase())){
                By = By.name(str_obj_Locators);
                return By;
            }
            else if("LINK".equals(str_locator_arr[0].toUpperCase())){
                By = By.linkText(str_obj_Locators);
                return By;
            }
            else if("CLASSNAME".equals(str_locator_arr[0].toUpperCase())){
                By = By.className(str_obj_Locators);
                return By;
            }
            return By;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            return null;
        }
    }
    //*******************************
    public boolean clickElement(By element,int seconds) {
        try
        {
            waitForVisibility(element,seconds);
            driver.findElement(element).click();
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
    public boolean submitElement(By element ,int seconds) {
        try
        {
            waitForVisibility(element,seconds);
            driver.findElement(element).submit();
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
    public WebElement getWebElement(By webElement ,int seconds)
    {
        WebElement element;
        try
        {
            waitForVisibility(webElement,seconds);
            element = driver.findElement(webElement);
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
    //TypeTextToElement
    public boolean typeTextToElement(By element,  String ValueToType)
    {
        try
        {
            waitForVisibility(element, 20);
            driver.findElement(element).click();
            driver.findElement(element).sendKeys(ValueToType);
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
    //GetTextFromElement
    public String getTextFromElement(By element)
    {
        String str_getText = "";
        try
        {
            waitForVisibility(element, 20);
            str_getText = driver.findElement(element).getText();
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
    //GetAttributeFromElement
    public String getAttributeFromElement(By element, String attributeName)
    {
        String str_getText = "";
        try
        {
            waitForVisibility(element, 20);
            str_getText = driver.findElement(element).getAttribute(attributeName);
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
    //getFutureYearFromCurrent
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
    //getPreviousYearFromCurrent
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
    //getFutureMonthFromCurrent
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
    //getFutureMonthFromCurrent
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
    //getTestDataFromJSON
    public String getTestDataFromJSON(String fileName, String JSONKey)
    {
        String strTestData = "";
        try
        {
            strTestData = extractValueFromJSONFile(fileName, JSONKey);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return strTestData;
    }

    //*******************************
    //getFrameSize
    public int getFrameSize()
    {
        int size=0;
        try {
            size = driver.findElements(By.tagName("iframe")).size();
        }
        catch(Exception ex)
        {
            logger.error("getFrameSize", "Error in getting iframe details" + ex.getMessage());
        }

        return size;
    }
    //*******************************
    public void switchToFrame(int frameid)
    {
        try {
            if(frameid<0)
                driver.switchTo().defaultContent();
            else
                driver.switchTo().frame(frameid);
        }
        catch(Exception ex)
        {
            logger.error("switchToFrame", "Error in switching to iframe" + ex.getMessage());
        }
    }
    //*******************************
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
    public boolean isWebElementNotDisplayed(By element, int seconds)
    {
        boolean flag = false;
        try
        {
            waitForVisibility(element,seconds);
            if (driver.findElement(element).isDisplayed())
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
     * Returns the web element display status
     *
     * @param -locator webelement to find
     * @return returns the webelement status
     */
    public boolean isWebElementDisplayed(By element, int seconds)
    {
        boolean flag = false;
        try
        {
            waitForVisibility(element,seconds);
            if (driver.findElement(element).isDisplayed())
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
    // Find multiple elements with similar xpath
    public List<WebElement> getWebElements(By element)
    {
        List<WebElement> webElements = new ArrayList<WebElement>();
        try {
            webElements = driver.findElements(element);
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
    public String getScreenShot(String fileName) throws Exception
    {
        String folderName = "Failedscreenshots";
        File srcFile = null;
        String screenShotPath = null;
        if(driver!=null)
        {
            TakesScreenshot scrShot =((TakesScreenshot)driver);
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
}
