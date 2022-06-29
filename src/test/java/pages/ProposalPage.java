package pages;

import Utils.Utils;
import Utils.GlobalValues;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import tests.BaseClass;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static tests.BaseClass.*;

public class ProposalPage
{
    Utils utils = new Utils();

    public final Logger logger = LoggerFactory.getLogger(ProposalPage.class);

    By proposal_Link = By.xpath("//span[contains(text(),'Proposal')]");
    By submitYourProposal_Text = By.xpath("//h2[contains(text(),'Submit Your Proposal')]");
    By projectTitle_Edit = By.xpath("//input[@id='react-project-title']");
    By startDate_Edit = By.xpath("//input[@id='react-project-start_date']");
    By endDate_Edit = By.xpath("//input[@id='react-project-end_date']"); // todays Date - not able to select
    By projectDuration_Text = By.xpath("//div[@id='']");
    By projectDescription_Edit = By.xpath("//textarea[@id='react-project-description']"); // 4000 chars

    By activityDropDownArrow_Btn = By.id("react-select-project-activity--value");
    By activitySelectedItem_Text = By.id("react-select-project-activity--value-item");
    By targetMarketSelectedItem_Text = By.id("react-select-project-primary_market--value-item");
    By targetMarketDropDownArrow_Btn = By.id("react-select-project-primary_market--value");
    By activityType_Edit = By.id("react-project-entity_type");
    By shareHoldingPercentage_Edit = By.id("react-project-shareholding_percentage"); //alphabets not allowed
    By lastQuestionYes_Btn = By.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[12]/div[1]/div[2]/label[1]/span[1]");
    By lastQuestionNo_Btn = By.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[12]/div[1]/div[2]/label[2]/span[1]");
    By selectFiles_Btn = By.id("react-project-attachments-btn");
    By selectFiles_Input = By.id("react-project-attachments-input");
    By Remarks_Edit = By.xpath("//textarea[@id='react-project-remarks']");
    By startDateAlertText = By.xpath("//*[contains(text(),'Must be today or later')]");
    By startDateWrongAlertText = By.id("react-project-start_date-alert");
    // This date doesn't look right - react-project-start_date-alert
    By proposalFormErrorNumber_Text = By.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/ul[1]/li[3]/a[1]/div[1]/span[1]");

    By startDateEmptyAlert_Text = By.xpath("//*[contains(text(), 'We need a response for this field')]");
    By remove_Btn = By.xpath("//*[@id='react-project-attachments-0-btn-remove']");
    By delete_Btn = By.xpath("//*[@class='bgp-btn bgp-btn-modal-delete']");
    //*******************************
    /**
     * clickAndVerifyProposalPage - Function to clickAndVerifyProposalPage
     * @param - nothing
     * @return true or false
     */
    public boolean clickAndVerifyProposalPage()
    {
        boolean status;
        try
        {
//            utils.clickElement(proposal_Link, 25);
            Thread.sleep(2000);
            BaseClass.getDriver().findElement(By.xpath("//span[contains(text(),'Proposal')]")).click();
            Assert.assertTrue(utils.isWebElementDisplayed(submitYourProposal_Text, 15));
            logger.info("Successfully Launched ContactDetails Page");

            status = true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            status = false;
        }
        return status;
    }

    //*******************************
    /**
     * enterProposalDetails - Function to enterProposalDetails
     * @param - nothing
     * @return true or false
     */
    public boolean enterProposalDetails()
    {
        boolean status;
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        try
        {
            Actions keys = new Actions(BaseClass.getDriver());
            DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
            Date date = new Date();
            Thread.sleep(2000);
            Assert.assertTrue(utils.typeTextToElement(projectTitle_Edit,utils.getTestDataFromJSON("TD_ProposalPage","projectTitle")));
            Thread.sleep(1000);
            Assert.assertTrue(utils.typeTextToElement(startDate_Edit, new SimpleDateFormat("dd MMM yyyy").format(new Date())));
            Thread.sleep(1000);
            Assert.assertTrue(utils.typeTextToElement(endDate_Edit, utils.getFutureMonthFromCurrent(10, "dd MMM yyyy")));
            Assert.assertEquals(utils.getTextFromElement(projectDuration_Text),utils.getTestDataFromJSON("TD_ProposalPage","projectDuration"));
            Assert.assertTrue(utils.typeTextToElement(projectDescription_Edit, utils.getTestDataFromJSON("TD_ProposalPage", "projectDescription")));
            js.executeScript("arguments[0].scrollIntoView();", BaseClass.getDriver().findElement(projectDescription_Edit));
            Thread.sleep(1000);
            Assert.assertTrue(utils.clickElement(activityDropDownArrow_Btn, 15));
            Thread.sleep(2000);
            keys.sendKeys(Keys.chord(Keys.DOWN, Keys.UP, Keys.DOWN, Keys.ENTER)).perform();
            Thread.sleep(1000);
            Assert.assertEquals(utils.getTextFromElement(activitySelectedItem_Text), utils.getTestDataFromJSON("TD_ProposalPage","Activity"));
            Assert.assertTrue(utils.typeTextToElement(activityType_Edit,utils.getTestDataFromJSON("TD_ProposalPage","activityType")));
            Assert.assertTrue(utils.typeTextToElement(shareHoldingPercentage_Edit,utils.getTestDataFromJSON("TD_ProposalPage","shareHoldingPercentage")));
            Thread.sleep(1000);
            Assert.assertTrue(utils.clickElement(By.xpath("//*[@class='Select is-clearable is-searchable Select--single']"), 15));
            Thread.sleep(1000);
            keys.sendKeys(Keys.chord(Keys.DOWN, Keys.DOWN, Keys.DOWN, Keys.ENTER)).perform();
            Thread.sleep(1000);
            Assert.assertEquals(utils.getTextFromElement(targetMarketSelectedItem_Text), utils.getTestDataFromJSON("TD_ProposalPage","targetMarketSelectedItem"));
            Assert.assertTrue(utils.clickElement(lastQuestionYes_Btn, 15));
            Thread.sleep(1000);
            js.executeScript("arguments[0].scrollIntoView();", BaseClass.getDriver().findElement(selectFiles_Btn));
            Thread.sleep(2000);

            if(System.getProperty("os.name").toLowerCase().contains("mac"))
            {
                BaseClass.getDriver().findElement(selectFiles_Input).sendKeys(ROOTPATH + "/src/test/resources/InputFiles/WGP_File.pdf");
            }
            else
            {
                File file = new File(ROOTPATH + "/src/test/resources/InputFiles/WGP_File.pdf");
                String filepath = file.getAbsolutePath();
                BaseClass.getDriver().findElement(selectFiles_Input).sendKeys(filepath);
            }
//            BaseClass.getDriver().findElement(selectFiles_Input).sendKeys(ROOTPATH + "/src/test/resources/InputFiles/WGP_File.pdf");
            Thread.sleep(2000);

            Assert.assertTrue(utils.typeTextToElement(Remarks_Edit, utils.getTestDataFromJSON("TD_ProposalPage", "proposalRemarks")));
            Assert.assertTrue(utils.clickElement(GlobalValues.save_Btn, 15));
            utils.isWebElementDisplayed(GlobalValues.draftSaved_Text, 10);
            status = true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            status = false;
        }
        return status;
    }

    //*******************************
    /**
     * enterProposalDetails - Function to enterProposalDetails
     * @param - nothing
     * @return true or false
     */
    public boolean validateProposalDetails()
    {
        boolean status;
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        try
        {
            Actions keys = new Actions(BaseClass.getDriver());
            Thread.sleep(2000);
            Assert.assertTrue(utils.typeTextToElement(projectTitle_Edit,utils.getTestDataFromJSON("TD_ProposalPage","projectTitle")));
            Thread.sleep(1000);
            Assert.assertTrue(utils.typeTextToElement(startDate_Edit, new SimpleDateFormat("dd MMM yyyy").format(new Date())));
            Thread.sleep(1000);
            Assert.assertTrue(utils.typeTextToElement(endDate_Edit, utils.getFutureMonthFromCurrent(10, "dd MMM yyyy")));
            Assert.assertEquals(utils.getTextFromElement(projectDuration_Text),utils.getTestDataFromJSON("TD_ProposalPage","projectDuration"));
            Assert.assertTrue(utils.typeTextToElement(projectDescription_Edit, utils.getTestDataFromJSON("TD_ProposalPage", "projectDescription")));
            js.executeScript("arguments[0].scrollIntoView();", BaseClass.getDriver().findElement(projectDescription_Edit));
            Thread.sleep(1000);
            Assert.assertTrue(utils.clickElement(activityDropDownArrow_Btn, 15));
            Thread.sleep(2000);
            keys.sendKeys(Keys.chord(Keys.DOWN, Keys.UP, Keys.DOWN, Keys.ENTER)).perform();
            Thread.sleep(1000);
            Assert.assertEquals(utils.getTextFromElement(activitySelectedItem_Text), utils.getTestDataFromJSON("TD_ProposalPage","Activity"));
            Assert.assertTrue(utils.typeTextToElement(activityType_Edit,utils.getTestDataFromJSON("TD_ProposalPage","activityType")));
            Assert.assertTrue(utils.typeTextToElement(shareHoldingPercentage_Edit,utils.getTestDataFromJSON("TD_ProposalPage","shareHoldingPercentage")));
            Thread.sleep(1000);
            Assert.assertTrue(utils.clickElement(By.xpath("//*[@class='Select is-clearable is-searchable Select--single']"), 15));
            Thread.sleep(1000);
            keys.sendKeys(Keys.chord(Keys.DOWN, Keys.DOWN, Keys.DOWN, Keys.ENTER)).perform();
            Thread.sleep(1000);
            Assert.assertEquals(utils.getTextFromElement(targetMarketSelectedItem_Text), utils.getTestDataFromJSON("TD_ProposalPage","targetMarketSelectedItem"));
            Assert.assertTrue(utils.clickElement(lastQuestionYes_Btn, 15));
            Thread.sleep(1000);
            js.executeScript("arguments[0].scrollIntoView();", BaseClass.getDriver().findElement(selectFiles_Btn));
            Thread.sleep(2000);

            if(System.getProperty("os.name").toLowerCase().contains("mac"))
            {
                BaseClass.getDriver().findElement(selectFiles_Input).sendKeys(ROOTPATH + "/src/test/resources/InputFiles/WGP_File.docx");
            }
            else
            {
                File file = new File(ROOTPATH + "/src/test/resources/InputFiles/WGP_File.docx");
                String filepath = file.getAbsolutePath();
                BaseClass.getDriver().findElement(selectFiles_Input).sendKeys(filepath);
            }
//            BaseClass.getDriver().findElement(selectFiles_Input).sendKeys(ROOTPATH + "/src/test/resources/InputFiles/WGP_File.pdf");
            Thread.sleep(2000);
            utils.clickElement(remove_Btn, 15);
            Thread.sleep(1000);
            utils.isWebElementDisplayedByText("Delete Confirmation", 10);
            utils.clickElement(delete_Btn, 15);
            Thread.sleep(2000);
            Assert.assertTrue(utils.typeTextToElement(Remarks_Edit, utils.getTestDataFromJSON("TD_ProposalPage", "proposalRemarks")));
            Assert.assertTrue(utils.clickElement(GlobalValues.save_Btn, 15));
            utils.isWebElementDisplayed(GlobalValues.draftSaved_Text, 10);
            status = true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            status = false;
        }
        return status;
    }

    //*******************************
    /**
     * verifyDates - Function to verifyDates
     * @param - nothing
     * @return true or false
     */
    public boolean verifyDates()
    {
        boolean status;
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        Actions keys = new Actions(BaseClass.getDriver());
        try
        {
            Thread.sleep(2000);
            js.executeScript("arguments[0].scrollIntoView();", BaseClass.getDriver().findElement(submitYourProposal_Text));
            utils.clearEntireText(startDate_Edit);
            BaseClass.getDriver().findElement(startDate_Edit).sendKeys(Keys.TAB);
            Thread.sleep(1000);
            String startDatetxt = utils.getAttributeFromElement(startDate_Edit, "Value");
            if (startDatetxt.length() > 0)
            {
                utils.clickElement(startDate_Edit, 10);
                for (int i = 1; i<=startDatetxt.length(); i++)
                {
                    Thread.sleep(1000);
                    keys.sendKeys(Keys.chord(Keys.SHIFT, Keys.CONTROL, Keys.LEFT, Keys.DELETE)).perform();
                }
            }
            else
            {
                logger.info("StartDate Edit is empty ");
            }
            utils.isWebElementDisplayed(startDateEmptyAlert_Text, 10);
            utils.typeTextToElement(startDate_Edit, utils.getPreviousYearFromCurrent(1, "dd MMM yyyy"));
            Thread.sleep(1000);
            utils.isWebElementDisplayed(startDateAlertText, 10);
            Assert.assertTrue(utils.typeTextToElement(startDate_Edit, new SimpleDateFormat("dd MMM yyyy").format(new Date())));
            Assert.assertEquals(utils.getTextFromElement(startDateWrongAlertText), "This date doesn't look right");
            status = true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            status = false;
        }
        return status;
    }

    //*******************************
    /**
     * enterStartDateAfterReview - Function to enterStartDateAfterReview
     * @param - nothing
     * @return true or false
     */
    public boolean enterStartDateAfterReview()
    {
        boolean status;
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        try
        {
            Thread.sleep(2000);
            Assert.assertEquals(utils.getTextFromElement(proposalFormErrorNumber_Text), utils.getTestDataFromJSON("TD_ProposalPage","proposalFormErrorNumber"));
            Assert.assertTrue(utils.isWebElementDisplayed(startDateEmptyAlert_Text, 10));
            Assert.assertTrue(utils.typeTextToElement(startDate_Edit, new SimpleDateFormat("dd MMM yyyy").format(new Date())));
            js.executeScript("arguments[0].scrollIntoView();", BaseClass.getDriver().findElement(Remarks_Edit));
            Assert.assertTrue(utils.clickElement(GlobalValues.save_Btn, 15));
            Assert.assertTrue(utils.clickElement(GlobalValues.next_Btn, 15));
            status = true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            status = false;
        }
        return status;
    }
}

