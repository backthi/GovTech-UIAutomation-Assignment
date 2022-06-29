package pages;

import Utils.Utils;
import Utils.GlobalValues;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import tests.BaseClass;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import static tests.BaseClass.*;

public class DeclareAndReviewPage
{
    Utils utils = new Utils();

    public final Logger logger = LoggerFactory.getLogger(EligibilityPage.class);

    By declareAndAcknowledgeTerms_Text = By.xpath("//h2[contains(text(),'Declare & Acknowledge Terms')]");

    By secondQuestion_Text = By.xpath("//*[contains(text(),'Has the applicant been or is currently being engaged in any civil suit or proceedings in any jurisdiction in the last 5 years?')]");
    By firstQuestion_No_RadioBtn = By.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/ol[1]/li[1]/div[1]/div[2]/label[1]/span[1]");
    By secondQuestion_No_RadioBtn = By.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/ol[1]/li[2]/div[1]/div[2]/label[1]/span[1]");

    By thirdQuestion_No_RadioBtn = By.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/ol[1]/li[3]/div[1]/div[2]/label[1]/span[1]");
    By fourthQuestion_No_RadioBtn = By.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/ol[1]/li[4]/div[1]/div[2]/label[1]/span[1]");
    By fifthQuestion_No_RadioBtn = By.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/ol[1]/li[5]/div[1]/div[2]/label[1]/span[1]");
    By fifthQuestion_Yes_RadioBtn = By.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/ol[1]/li[5]/div[1]/div[2]/label[2]/span[1]");
    By fifthQuestionYesIncentiveName_Edit = By.xpath("//input[@id='react-declaration-other_incentives-0-name']");
    By fifthQuestionYesIncentiveStartDate_Edit = By.id("react-declaration-other_incentives-0-start_date");
    By fifthQuestionYesIncentiveEndDate_Edit = By.id("react-declaration-other_incentives-0-end_date");
    By sixthQuestion_No_RadioBtn = By.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/ol[1]/li[6]/div[1]/div[2]/label[1]/span[1]");
    By seventhQuestion_No_RadioBtn = By.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/ol[1]/li[7]/div[1]/div[2]/label[1]/span[1]");
    By eighthQuestion_No_RadioBtn = By.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/ol[1]/li[8]/div[1]/div[2]/label[1]/span[1]");
    By eighthQuestion_Yes_RadioBtn = By.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/ol[1]/li[8]/div[1]/div[2]/label[2]/span[1]");
    By eighthQuestionPleaseDisclose_Edit = By.id("react-declaration-debarment_remarks");
    By eighthQuestionAlertMessage_Text = By.xpath("//*[contains(text(),'Please note that debarred entities or individuals by Enterprise Singapore are not allowed to participate in all Enterprise Singapore funded projects.')]");
    // Please note that debarred entities or individuals by Enterprise Singapore are not allowed to participate in all Enterprise Singapore funded projects.
    By ninthQuestion_No_RadioBtn = By.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/ol[1]/li[9]/div[1]/div[2]/label[1]/span[1]");

    By tenthQuestion_No_RadioBtn = By.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/ol[1]/li[10]/div[1]/div[2]/label[1]/span[1]");
    By ninthQuestion_Yes_RadioBtn = By.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/ol[1]/li[9]/div[1]/div[2]/label[2]/span[1]");

    By tenthQuestion_Yes_RadioBtn = By.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/ol[1]/li[10]/div[1]/div[2]/label[2]/span[1]");
    By consentAndAcknowlegment_Text = By.xpath("//*[contains(text(), 'Consent & Acknowledgement')]");
    By theApplicantHerebyAcknowledge_ChkBox = By.id("react-declaration-consent_acknowledgement_check");
    By ninthQuestionNoAlertMessage_Text = By.xpath("//body[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/ol[1]/h1[1]/span[1]");
    By tenthQuestionNoAlertMessage_Text = By.xpath("//body[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/ol[1]/h1[2]/span[1]");
    By review_Btn = By.id("review-btn");
    By reviewYourApplication_Text = By.xpath("//*[contains(text(),'Review Your Application')]");
    By applicationSubmitted_Text = By.xpath("//h3[contains(text(),'Your application has been submitted.')]");
    By submittedStatus_Text = By.xpath("//*[contains(text(),'Submitted')]");
    By agencyDetails_Text = By.xpath("//*[contains(text(),'Enterprise Singapore')]");
    By RefID_Text = By.xpath("//table[@class='key-status-section']//tbody//tr//td[@class='value']");
    By finalDeclaration_ChkBox = By.id("react-declaration-info_truthfulness_check");
    By submit_Btn = By.id("submit-btn");
    By myGrantsDashBoard_Text = By.xpath("//*[contains(text(),'My Grants')]");
    By myApplicationsSection_Text = By.xpath("//h3[contains(text(),'My Applications')]");
    By viewMySFEC_Text = By.xpath("//button[@id='show-credit-info']");
    By processingTab_Text = By.xpath("//a[contains(text(),'Processing (')]");
    By myGrantsPageQues_Text = By.xpath("//h4[contains(text(),'Edit company profile')]");


    //*******************************
    /**
     * clickAndVerifyDeclareAndVerify - Function to clickAndVerifyDeclareAndVerify
     * @param - nothing
     * @return true or false
     */
    public boolean clickAndVerifyDeclareAndVerify() {
        boolean status;
        try
        {
            Thread.sleep(2000);
            BaseClass.getDriver().findElement(By.xpath("//span[contains(text(),'Declare & Review')]")).click();
            Assert.assertTrue(utils.isWebElementDisplayed(declareAndAcknowledgeTerms_Text, 15));
            logger.info("Successfully Launched Declare & Review Page");

            status = true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
            e.getMessage();
            status = false;
        }
        return status;
    }

    //*******************************
    /**
     * fillingAllQuestions - Function for fillingAllQuestions in Declare And Review Page
     * @param - nothing
     * @return true or false
     */
    public boolean fillingAllQuestions() {
        boolean status;
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        try
        {
            Thread.sleep(2000);
            Assert.assertTrue(utils.clickElement(firstQuestion_No_RadioBtn,15));
            Assert.assertTrue(utils.clickElement(secondQuestion_No_RadioBtn,15));
            Assert.assertTrue(utils.clickElement(thirdQuestion_No_RadioBtn,15));
            Assert.assertTrue(utils.clickElement(fourthQuestion_No_RadioBtn,15));
            Assert.assertTrue(utils.clickElement(fifthQuestion_No_RadioBtn,15));
            Assert.assertTrue(utils.clickElement(sixthQuestion_No_RadioBtn,15));
            Assert.assertTrue(utils.clickElement(seventhQuestion_No_RadioBtn,15));
            Assert.assertTrue(utils.clickElement(eighthQuestion_No_RadioBtn,15));
            Assert.assertTrue(utils.clickElement(ninthQuestion_No_RadioBtn,15));
            Assert.assertTrue(utils.isWebElementDisplayed(ninthQuestionNoAlertMessage_Text, 10));
            Assert.assertTrue(utils.clickElement(tenthQuestion_No_RadioBtn,15));
            Assert.assertTrue(utils.isWebElementDisplayed(tenthQuestionNoAlertMessage_Text, 10));
            Assert.assertTrue(utils.clickElement(ninthQuestion_Yes_RadioBtn,15));
            Assert.assertTrue(utils.clickElement(tenthQuestion_Yes_RadioBtn,15));
            Thread.sleep(1000);
            js.executeScript("arguments[0].scrollIntoView();", BaseClass.getDriver().findElement(review_Btn));
            Assert.assertTrue(utils.clickElement(theApplicantHerebyAcknowledge_ChkBox,15));
            Assert.assertTrue(utils.clickElement(GlobalValues.save_Btn, 15));
            Assert.assertTrue(utils.isWebElementDisplayed(GlobalValues.draftSaved_Text, 20));
            status = true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
            e.getMessage();
            status = false;
        }
        return status;
    }

    //*******************************
    /**
     * fillingAllQuestionsWithOtherOptions - Function for fillingAllQuestionsWithOtherOptions in Declare And Review Page
     * @param - nothing
     * @return true or false
     */
    public boolean fillingAllQuestionsWithOtherOptions() {
        boolean status;
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        try
        {
            Thread.sleep(2000);
            Assert.assertTrue(utils.clickElement(firstQuestion_No_RadioBtn,15));
            Assert.assertTrue(utils.clickElement(secondQuestion_No_RadioBtn,15));
            Assert.assertTrue(utils.clickElement(thirdQuestion_No_RadioBtn,15));
            Assert.assertTrue(utils.clickElement(fourthQuestion_No_RadioBtn,15));
            Assert.assertTrue(utils.clickElement(fifthQuestion_Yes_RadioBtn,15));
            Assert.assertTrue(utils.typeTextToElement(fifthQuestionYesIncentiveName_Edit,utils.getTestDataFromJSON("TD_DeclareAndReviewPage","fifthQuestionYesIncentiveName")));
            Assert.assertTrue(utils.typeTextToElement(fifthQuestionYesIncentiveStartDate_Edit,new SimpleDateFormat("dd MMM yyyy").format(new Date())));
            Assert.assertTrue(utils.typeTextToElement(fifthQuestionYesIncentiveEndDate_Edit,utils.getFutureMonthFromCurrent(10, "dd MMM yyyy")));
            Assert.assertTrue(utils.clickElement(fifthQuestion_No_RadioBtn,15));
            Assert.assertTrue(utils.clickElement(sixthQuestion_No_RadioBtn,15));
            Assert.assertTrue(utils.clickElement(seventhQuestion_No_RadioBtn,15));
            Assert.assertTrue(utils.clickElement(eighthQuestion_Yes_RadioBtn,15));
            Assert.assertTrue(utils.typeTextToElement(eighthQuestionPleaseDisclose_Edit,utils.getTestDataFromJSON("TD_DeclareAndReviewPage","eighthQuestionPleaseDisclose")));
            Assert.assertTrue(utils.isWebElementDisplayed(eighthQuestionAlertMessage_Text,15));
            Assert.assertTrue(utils.clickElement(eighthQuestion_No_RadioBtn,15));
            Assert.assertTrue(utils.clickElement(ninthQuestion_No_RadioBtn,15));
            Assert.assertTrue(utils.isWebElementDisplayed(ninthQuestionNoAlertMessage_Text, 10));
            Assert.assertTrue(utils.clickElement(tenthQuestion_No_RadioBtn,15));
            Assert.assertTrue(utils.isWebElementDisplayed(tenthQuestionNoAlertMessage_Text, 10));
            Assert.assertTrue(utils.clickElement(ninthQuestion_Yes_RadioBtn,15));
            Assert.assertTrue(utils.clickElement(tenthQuestion_Yes_RadioBtn,15));
            Thread.sleep(1000);
            js.executeScript("arguments[0].scrollIntoView();", BaseClass.getDriver().findElement(review_Btn));
            Thread.sleep(1000);
            Assert.assertTrue(utils.clickElement(theApplicantHerebyAcknowledge_ChkBox,15));
            Assert.assertTrue(utils.clickElement(GlobalValues.save_Btn, 15));
            utils.isWebElementDisplayed(GlobalValues.draftSaved_Text, 20);
            status = true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
            e.getMessage();
            status = false;
        }
        return status;
    }

    //*******************************
    /**
     * clickOnReview - Function to clickOnReview in Declare And Review Page
     * @param - nothing
     * @return true or false
     */
    public boolean clickOnReview() {
        boolean status;
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        try
        {
            Thread.sleep(2000);
            js.executeScript("arguments[0].scrollIntoView();", BaseClass.getDriver().findElement(review_Btn));
            Assert.assertTrue(utils.clickElement(review_Btn,15));

            status = true;
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {
            BaseClass.getDriver().navigate().refresh();
            js.executeScript("arguments[0].scrollIntoView();", BaseClass.getDriver().findElement(review_Btn));
            Assert.assertTrue(utils.clickElement(review_Btn,15));
            status = false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
            e.getMessage();
            status = false;
        }
        return status;
    }

    //*******************************
    /**
     * clickOnDeclareAndReviewAndReview - Function to clickOnDeclareAndReviewAndReview
     * @param - nothing
     * @return true or false
     */
    public boolean clickOnDeclareAndReviewAndReview() {
        boolean status;
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        try
        {
            Thread.sleep(2000);
            BaseClass.getDriver().findElement(By.xpath("//span[contains(text(),'Declare & Review')]")).click();
            Assert.assertTrue(utils.isWebElementDisplayed(declareAndAcknowledgeTerms_Text, 25));
            utils.clickElement(secondQuestion_Text, 10);
            Thread.sleep(2000);
            js.executeScript("arguments[0].scrollIntoView();", BaseClass.getDriver().findElement(theApplicantHerebyAcknowledge_ChkBox));
            Assert.assertTrue(utils.clickElement(review_Btn,15));
            status = true;
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {
            BaseClass.getDriver().navigate().refresh();
            js.executeScript("arguments[0].scrollIntoView();", BaseClass.getDriver().findElement(review_Btn));
            Assert.assertTrue(utils.clickElement(review_Btn,15));
            status = false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
            e.getMessage();
            status = false;
        }
        return status;
    }

    //*******************************
    /**
     * reviewAllData - Function to reviewAllData
     * @param - nothing
     * @return true or false
     */
    public boolean reviewAllData()
    {
        boolean status;
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        try
        {
            Thread.sleep(2000);
            Assert.assertTrue(utils.isWebElementDisplayed(reviewYourApplication_Text, 30));
            js.executeScript("arguments[0].scrollIntoView();",BaseClass.getDriver().findElement(By.xpath("//h2[contains(text(),'Contact Details')]")));
            HashMap<String, String> contactDetailsMap = new HashMap<>();
            contactDetailsMap.put("personName","TD_ContactDetailsPage");
            contactDetailsMap.put("personJobTitle","TD_ContactDetailsPage");
            contactDetailsMap.put("personContactNo","TD_ContactDetailsPage");
            contactDetailsMap.put("personEmail","TD_ContactDetailsPage");
            contactDetailsMap.put("personAlternateEmail","TD_ContactDetailsPage");
            Assert.assertTrue(utils.verifyHashMapValue(contactDetailsMap));
            js.executeScript("arguments[0].scrollIntoView();",BaseClass.getDriver().findElement(By.xpath("//h3[contains(text(),'Letter Of Offer Addressee')]")));
            contactDetailsMap.put("personName","TD_ContactDetailsPage");
            contactDetailsMap.put("personJobTitle","TD_ContactDetailsPage");
            contactDetailsMap.put("personEmail","TD_ContactDetailsPage");
            Assert.assertTrue(utils.verifyHashMapValue(contactDetailsMap));
            Thread.sleep(1000);
            js.executeScript("arguments[0].scrollIntoView();",BaseClass.getDriver().findElement(By.xpath("//h2[contains(text(),'Proposal')]")));
            HashMap<String, String> proposalMap = new HashMap<>();
            proposalMap.put("projectTitle","TD_ProposalPage");
            proposalMap.put("projectDescription","TD_ProposalPage");
            proposalMap.put("projectDuration","TD_ProposalPage");
            proposalMap.put("Activity","TD_ProposalPage");
            proposalMap.put("activityType","TD_ProposalPage");
            Assert.assertTrue(utils.verifyHashMapValue(proposalMap));
            js.executeScript("arguments[0].scrollIntoView();",BaseClass.getDriver().findElement(By.xpath("//*[contains(text(),'Target Market')]")));
            proposalMap.put("proposalRemarks","TD_ProposalPage");
            Assert.assertTrue(utils.verifyHashMapValue(proposalMap));
            Thread.sleep(1000);
            js.executeScript("arguments[0].scrollIntoView();",BaseClass.getDriver().findElement(By.xpath("//h2[contains(text(),'Business Impact')]")));
            HashMap<String, String> BusinessImpactMap = new HashMap<>();
            BusinessImpactMap.put("overseasSales1stEdit","TD_BusinessImpactPage");
            BusinessImpactMap.put("overseasSales2ndEdit","TD_BusinessImpactPage");
            BusinessImpactMap.put("overseasSales3rdEdit","TD_BusinessImpactPage");
            BusinessImpactMap.put("overseasSales4thEdit","TD_BusinessImpactPage");
            BusinessImpactMap.put("overseasInvestments1stEdit","TD_BusinessImpactPage");
            BusinessImpactMap.put("overseasInvestments2ndEdit","TD_BusinessImpactPage");
            BusinessImpactMap.put("overseasInvestments3rdEdit","TD_BusinessImpactPage");
            BusinessImpactMap.put("overseasInvestments4thEdit","TD_BusinessImpactPage");
            BusinessImpactMap.put("rationaleForProjectionsEdit","TD_BusinessImpactPage");
            Assert.assertTrue(utils.verifyHashMapValue(BusinessImpactMap));
            Thread.sleep(1000);
            js.executeScript("arguments[0].scrollIntoView();", BaseClass.getDriver().findElement(By.xpath("//h2[contains(text(),'Cost')]")));
            HashMap<String, String> costMap = new HashMap<>();
            costMap.put("thirdPartyNameOfVendor","TD_CostPage");
            costMap.put("thirdPartyOverseas","TD_CostPage");
            costMap.put("thirdPartyEstimatedCost","TD_CostPage");
            Assert.assertTrue(utils.verifyHashMapValue(costMap));
            costMap.put("thirdPartyRemarks","TD_CostPage");
            Assert.assertTrue(utils.verifyHashMapValue(costMap));
            Thread.sleep(1000);
            js.executeScript("arguments[0].scrollIntoView();", BaseClass.getDriver().findElement(By.xpath("//div[contains(text(), 'Office Space Rental')]")));
            costMap.put("officeRentalDescription","TD_CostPage");
            costMap.put("officeRentalDuration","TD_CostPage");
            costMap.put("officeRentalsMonthlyRentalCostVerify","TD_CostPage");
            costMap.put("officeRentalsEstimatedTotalCostVerify","TD_CostPage");
            costMap.put("officeRentalRemarks","TD_CostPage");
            Assert.assertTrue(utils.verifyHashMapValue(costMap));
            Thread.sleep(1000);
            js.executeScript("arguments[0].scrollIntoView();", BaseClass.getDriver().findElement(By.xpath("//div[contains(text(), 'Salary')]")));
            costMap.put("salaryName","TD_CostPage");
            costMap.put("salaryDesignation","TD_CostPage");
            costMap.put("salaryNationalityType","TD_CostPage");
            costMap.put("salaryRoleInProject","TD_CostPage");
            costMap.put("salaryProjectInvolvement","TD_CostPage");
            Assert.assertTrue(utils.verifyHashMapValue(costMap));
            Thread.sleep(1000);
            js.executeScript("arguments[0].scrollIntoView();", BaseClass.getDriver().findElement(By.id("react-project_cost-salaries-0-salary_in_billing_currency_label")));
            costMap.put("salaryEstimatedCostVerify","TD_CostPage");
            costMap.put("salaryMonthlySalaryVerify","TD_CostPage");
            costMap.put("salaryRemarks","TD_CostPage");
            Assert.assertTrue(utils.verifyHashMapValue(costMap));
            Thread.sleep(1000);
            js.executeScript("arguments[0].scrollIntoView();", BaseClass.getDriver().findElement(By.xpath("//h2[contains(text(),'Declare & Review')]")));
            Thread.sleep(1000);
            js.executeScript("arguments[0].scrollIntoView();", BaseClass.getDriver().findElement(finalDeclaration_ChkBox));
            Assert.assertTrue(utils.clickElement(finalDeclaration_ChkBox, 15));
            Assert.assertTrue(utils.clickElement(submit_Btn, 15));
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
     * verifyApplicationSubmitted - Function to verifyApplicationSubmitted
     * @param - nothing
     * @return true or false
     */
    public boolean verifyApplicationSubmitted() {
        boolean status;
        String refID;
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        try
        {
            Thread.sleep(2000);
            Assert.assertTrue(utils.isWebElementDisplayed(applicationSubmitted_Text, 30));
            Assert.assertTrue(utils.isWebElementDisplayed(submittedStatus_Text, 20));
            Assert.assertTrue(utils.isWebElementDisplayed(agencyDetails_Text, 20));
            refID = utils.getTextFromElement(RefID_Text);
            Assert.assertTrue(utils.clickElement(myGrantsDashBoard_Text, 20));
            Thread.sleep(3000);
            utils.isWebElementDisplayed(myGrantsPageQues_Text, 15);
            js.executeScript("arguments[0].scrollIntoView();", BaseClass.getDriver().findElement(viewMySFEC_Text));
            Assert.assertTrue(utils.clickElement(processingTab_Text, 20));
            By refID_text = By.xpath("//*[contains(text(),'" + refID + "')]");
            Assert.assertTrue(utils.isWebElementDisplayed(refID_text, 20));
            status = true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
            e.getMessage();
            status = false;
        }
        return status;
    }
}