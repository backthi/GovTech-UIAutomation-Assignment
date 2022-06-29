package pages;

import Utils.GlobalValues;
import Utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import tests.BaseClass;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static tests.BaseClass.*;

public class CostPage
{
    Utils utils = new Utils();

    public final Logger logger = LoggerFactory.getLogger(CostPage.class);

    By cost_Link = By.xpath("//span[contains(text(),'Cost')]");
    By provideDetailsOfCost_Text = By.xpath("//h2[contains(text(),'Provide Details of Costs')]");
    By thirdPartyVendorsSection_Link = By.id("react-project_cost-vendors-accordion-header");
    By thirdPartyVendors_Text = By.xpath("//div[contains(text(),'Third Party Vendors')]");
    By offlineVendors_Edit = By.xpath("//div[@id='react-project_cost-office_rentals-accordion-header']");


    By addNewItem_Edit = By.xpath("//button[@id='react-project_cost-vendors-add-item']");
    By officeRentalsAddNewItem_Edit = By.id("react-project_cost-office_rentals-add-item");
    //  react-project_cost-office_rentals-add-item
    // //button[@id='react-project_cost-salaries-add-item']

    By salaryAddNewItem_Edit = By.id("react-project_cost-salaries-add-item");
    By whereIsYourVendorRegisteredOverseas_radioBtn = By.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/label[2]/span[1]");
    By nameOfVendor_Edit = By.xpath("//input[@id='react-project_cost-vendors-0-vendor_name']");
    By thirdPartyAttachFileWarningMessage_Text = By.xpath("//*[contains(text(),'Please provide the required document(s)')]");
    // We need a response for this field -  type something, delete all, then tab
    //  Please provide the required document(s) - for attachment
    By thirdPartySelectFiles_Btn = By.id("react-project_cost-vendors-0-attachments-btn");
    By thirdPartySelectFilesInput_Text = By.id("react-project_cost-vendors-0-attachments-input");
    By thirdPartyEstimatedCostBillingCurrency_Edit = By.xpath("//input[@id='react-project_cost-vendors-0-amount_in_billing_currency']"); // 2000
    By thirdPartyEstimatedCost_Edit = By.id("react-project_cost-vendors-0-estimated_cost");
    By thirdPartyRemarks_Edit = By.id("react-project_cost-vendors-0-remarks");
    // top - SGD 2,000.00 - text
    By estimatedCostDollars_Text = By.id("react-select-project-activity--value-item");

    //Office Space Rental
    By officeSpaceRental_Link = By.id("react-project_cost-office_rentals-accordion-header");

    By officeRentalDescription_Edit = By.id("react-project_cost-office_rentals-0-description");
    By officeRentalDuration_Edit = By.id("react-project_cost-office_rentals-0-rental_duration"); // 9
    By officeMonthlyRentalBillingCurrency_Edit = By.id("react-project_cost-office_rentals-0-amount_in_billing_currency");
    By officeRentalSelectFiles_Btn = By.id("react-project_cost-office_rentals-0-attachments-btn");
    By officeRentalSelectFilesInput_Edit = By.id("react-project_cost-office_rentals-0-attachments-input");
    By officeRentalRemarks_Edit = By.id("react-project_cost-office_rentals-0-remarks");
    //  SGD 2,000.00

    By officeRentalsMonthlyRentalCost_Text = By.xpath("//*[contains(text(),'SGD 2,000.00')]"); // SGD 2,000.00
    By officeRentalsEstimatedTotalCost_Text = By.id("react-project_cost-office_rentals-0-estimated_cost"); // SGD 18,000.00
    By officeRentalsSection_Text = By.xpath("//*[contains(text(),'Office space rental refers to only basic rental expenses.')]");


    // Salary
    By salary_Link = By.id("react-project_cost-salaries-accordion-header");
    By salaryName_Edit = By.id("react-project_cost-salaries-0-name"); //Pawan
    By salaryDesignation_Edit = By.id("react-project_cost-salaries-0-designation"); // Operating Officer
    By salaryPassportNo_Edit = By.id("react-project_cost-salaries-0-nric"); // Z4567347
    By salaryNationalityType_Edit = By.id("react-select-project_cost-salaries-0-nationality--value"); // drop down //Singaporean
    By salaryNationaltyTypeVerify_Text = By.id("react-select-project_cost-salaries-0-nationality--value-item"); // Singaporean // DOWN Key
    By salaryRoleInProject_Edit = By.id("react-project_cost-salaries-0-project_role"); // Should supervise and manage all the activities of Operation team
    By salaryProjectInvolvement_Edit = By.id("react-project_cost-salaries-0-involvement_months");
    By salaryMonthlySalaryInBillingCountry_Edit = By.id("react-project_cost-salaries-0-salary_in_billing_currency"); // 3,000.00
    By salaryMonthlySalary_Text = By.xpath("//*[contains(text(),'SGD 2,000.00')]");
    By salaryEstimatedCost_Text = By.id("react-project_cost-salaries-0-estimated_cost");
    By salarySelectFiles_Btn = By.id("react-project_cost-salaries-0-attachments-btn");
    By salarySelectFilesInput_File = By.id("react-project_cost-salaries-0-attachments-input");
    By salaryRemarks_Edit = By.id("react-project_cost-salaries-0-remarks");

    //*******************************
    /**
     * clickAndVerifyCostPage - Function to clickAndVerifyCostPage
     * @param - nothing
     * @return true or false
     */
    public boolean clickAndVerifyCostPage()
    {
        boolean status;
        try
        {
            Thread.sleep(1000);
            BaseClass.getDriver().findElement(By.xpath("//span[contains(text(),'Cost')]")).click();
            Assert.assertTrue(utils.isWebElementDisplayed(provideDetailsOfCost_Text, 15));
            logger.info("Successfully Launched Cost Page");

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
     * enterThirdPartyVendorsDetails - Function to enterThirdPartyVendorsDetails
     * @param - nothing
     * @return true or false
     */
    public boolean enterThirdPartyVendorsDetails()
    {
        boolean status;
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        try
        {
            Thread.sleep(2000);
            Assert.assertTrue(utils.clickElement(thirdPartyVendorsSection_Link, 20));
            Assert.assertTrue(utils.isWebElementDisplayed(thirdPartyVendors_Text, 10));
            Assert.assertTrue(utils.clickElement(addNewItem_Edit, 10));
            Assert.assertTrue(utils.clickElement(whereIsYourVendorRegisteredOverseas_radioBtn, 20));
            Thread.sleep(1000);
            Assert.assertTrue(utils.isWebElementDisplayed(thirdPartyAttachFileWarningMessage_Text,10));
            Assert.assertTrue(utils.typeTextToElement(nameOfVendor_Edit, utils.getTestDataFromJSON("TD_CostPage", "thirdPartyNameOfVendor")));
            js.executeScript("arguments[0].scrollIntoView();", BaseClass.getDriver().findElement(thirdPartySelectFiles_Btn));
            Thread.sleep(1000);
            if(System.getProperty("os.name").toLowerCase().contains("mac"))
            {
                BaseClass.getDriver().findElement(thirdPartySelectFilesInput_Text).sendKeys(ROOTPATH + "/src/test/resources/InputFiles/WGP_File.pdf");
            }
            else
            {
                File file = new File(ROOTPATH + "/src/test/resources/InputFiles/WGP_File.pdf");
                String filepath = file.getAbsolutePath();
                BaseClass.getDriver().findElement(thirdPartySelectFilesInput_Text).sendKeys(filepath);
            }
//            BaseClass.getDriver().findElement(thirdPartySelectFilesInput_Text).sendKeys(ROOTPATH + "/src/test/resources/InputFiles/WGP_File.pdf");
            Thread.sleep(1000);
            Assert.assertTrue(utils.typeTextToElement(thirdPartyEstimatedCostBillingCurrency_Edit,utils.getTestDataFromJSON("TD_CostPage", "thirdPartyMonthlySalaryInBillingCountry")));
            Assert.assertEquals(utils.getTextFromElement(thirdPartyEstimatedCost_Edit), utils.getTestDataFromJSON("TD_CostPage", "thirdPartyEstimatedCost"));
            Assert.assertTrue(utils.typeTextToElement(thirdPartyRemarks_Edit, utils.getTestDataFromJSON("TD_CostPage", "thirdPartyRemarks")));

            js.executeScript("arguments[0].scrollIntoView();", BaseClass.getDriver().findElement(provideDetailsOfCost_Text));
            Assert.assertTrue(utils.clickElement(thirdPartyVendorsSection_Link, 10));

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
     * enteringOfficeSpaceRentalDetails - Function to enteringOfficeSpaceRentalDetails
     * @param - nothing
     * @return true or false
     */
    public boolean enteringOfficeSpaceRentalDetails()
    {
        boolean status;
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        try
        {
            Thread.sleep(2000);
            Assert.assertTrue(utils.clickElement(officeSpaceRental_Link, 10));
            Assert.assertTrue(utils.isWebElementDisplayed(officeRentalsSection_Text, 10));
            Assert.assertTrue(utils.clickElement(officeRentalsAddNewItem_Edit, 10));
            Assert.assertTrue(utils.typeTextToElement(officeRentalDescription_Edit, utils.getTestDataFromJSON("TD_CostPage", "officeRentalDescription")));
            Thread.sleep(1000);
            Assert.assertTrue(utils.typeTextToElement(officeRentalDuration_Edit, utils.getTestDataFromJSON("TD_CostPage", "officeRentalDuration")));
            Assert.assertTrue(utils.typeTextToElement(officeMonthlyRentalBillingCurrency_Edit,utils.getTestDataFromJSON("TD_CostPage", "officeMonthlyRentalBillingCountry")));

            Assert.assertEquals(utils.getTextFromElement(officeRentalsMonthlyRentalCost_Text), utils.getTestDataFromJSON("TD_CostPage", "officeRentalsMonthlyRentalCostVerify"));
            Assert.assertEquals(utils.getTextFromElement(officeRentalsEstimatedTotalCost_Text), utils.getTestDataFromJSON("TD_CostPage", "officeRentalsEstimatedTotalCostVerify"));

            js.executeScript("arguments[0].scrollIntoView();", BaseClass.getDriver().findElement(officeRentalSelectFiles_Btn));
            if(System.getProperty("os.name").toLowerCase().contains("mac"))
            {
                BaseClass.getDriver().findElement(officeRentalSelectFilesInput_Edit).sendKeys(ROOTPATH + "/src/test/resources/InputFiles/WGP_File.docx");
            }
            else
            {
                File file = new File(ROOTPATH + "/src/test/resources/InputFiles/WGP_File.docx");
                String filepath = file.getAbsolutePath();
                BaseClass.getDriver().findElement(officeRentalSelectFilesInput_Edit).sendKeys(filepath);
            }
//            BaseClass.getDriver().findElement(officeRentalSelectFilesInput_Edit).sendKeys(ROOTPATH + "/src/test/resources/InputFiles/WGP_File.pdf");
            Assert.assertTrue(utils.typeTextToElement(officeRentalRemarks_Edit, utils.getTestDataFromJSON("TD_CostPage", "officeRentalRemarks")));

            js.executeScript("arguments[0].scrollIntoView();", BaseClass.getDriver().findElement(thirdPartyVendorsSection_Link));
            Assert.assertTrue(utils.clickElement(officeSpaceRental_Link, 10));
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
     * enteringSalaryDetails - Function to enteringSalaryDetails
     * @param - nothing
     * @return true or false
     */
    public boolean enteringSalaryDetails()
    {
        boolean status;
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        Actions keys = new Actions(BaseClass.getDriver());
        try
        {
            Thread.sleep(2000);
            Assert.assertTrue(utils.clickElement(salary_Link, 10));
            Assert.assertTrue(utils.clickElement(salaryAddNewItem_Edit, 10));
            Assert.assertTrue(utils.typeTextToElement(salaryName_Edit, utils.getTestDataFromJSON("TD_CostPage", "salaryName")));
            Thread.sleep(1000);
            js.executeScript("arguments[0].scrollIntoView();", BaseClass.getDriver().findElement(salary_Link));
            Assert.assertTrue(utils.typeTextToElement(salaryDesignation_Edit, utils.getTestDataFromJSON("TD_CostPage", "salaryDesignation")));
            Assert.assertTrue(utils.typeTextToElement(salaryPassportNo_Edit,utils.getTestDataFromJSON("TD_CostPage", "salaryPassportNo")));

            Assert.assertTrue(utils.clickElement(salaryNationalityType_Edit, 10));
            keys.sendKeys(Keys.chord(Keys.DOWN, Keys.UP, Keys.ENTER)).perform();
            Assert.assertEquals(utils.getTextFromElement(salaryNationaltyTypeVerify_Text), utils.getTestDataFromJSON("TD_CostPage", "salaryNationalityType"));

            Assert.assertTrue(utils.typeTextToElement(salaryRoleInProject_Edit, utils.getTestDataFromJSON("TD_CostPage", "salaryRoleInProject")));
            Assert.assertTrue(utils.typeTextToElement(salaryProjectInvolvement_Edit,utils.getTestDataFromJSON("TD_CostPage", "salaryProjectInvolvement")));
            Assert.assertTrue(utils.typeTextToElement(salaryMonthlySalaryInBillingCountry_Edit, utils.getTestDataFromJSON("TD_CostPage", "salaryMonthlySalaryInBillingCountry")));
            js.executeScript("arguments[0].scrollIntoView();", BaseClass.getDriver().findElement(salaryProjectInvolvement_Edit));
            Assert.assertTrue(utils.isWebElementDisplayed(salaryMonthlySalary_Text, 10));
            Assert.assertEquals(utils.getTextFromElement(salaryEstimatedCost_Text), utils.getTestDataFromJSON("TD_CostPage", "salaryEstimatedCostVerify"));

            js.executeScript("arguments[0].scrollIntoView();", BaseClass.getDriver().findElement(salarySelectFiles_Btn));
            if(System.getProperty("os.name").toLowerCase().contains("mac"))
            {
                BaseClass.getDriver().findElement(salarySelectFilesInput_File).sendKeys(ROOTPATH + "/src/test/resources/InputFiles/WGP_File.pdf");
            }
            else
            {
                File file = new File(ROOTPATH + "/src/test/resources/InputFiles/WGP_File.pdf");
                String filepath = file.getAbsolutePath();
                BaseClass.getDriver().findElement(salarySelectFilesInput_File).sendKeys(filepath);
            }
//            BaseClass.getDriver().findElement(salarySelectFilesInput_File).sendKeys(ROOTPATH + "/src/test/resources/InputFiles/WGP_File.docx");
            Assert.assertTrue(utils.typeTextToElement(salaryRemarks_Edit, utils.getTestDataFromJSON("TD_CostPage", "salaryRemarks")));

            js.executeScript("arguments[0].scrollIntoView();", BaseClass.getDriver().findElement(officeSpaceRental_Link));
            Assert.assertTrue(utils.clickElement(salary_Link, 10));
            Assert.assertTrue(utils.clickElement(GlobalValues.save_Btn, 15));
            utils.isWebElementDisplayed(GlobalValues.draftSaved_Text, 10);
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
