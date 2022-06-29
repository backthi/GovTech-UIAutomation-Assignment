package pages;

import Utils.GlobalValues;
import Utils.*;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import tests.BaseClass;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import static tests.BaseClass.driver;
import static tests.BaseClass.js;

public class BusinessImpactPage
{
    Utils utils = new Utils();
    Properties props = utils.readConfigFile();
    public final Logger logger = LoggerFactory.getLogger(BusinessImpactPage.class);

    By businessImpact_Link = By.xpath("//*[contains(text(),'Business Impact')]");
    By businessImpactTitle_Text = By.xpath("//*[contains(text(),'Explain The Business Impact')]");
    By fYEndDate_Edit = By.id("react-project_impact-fy_end_date_0");
    By currentFy_Text = By.xpath("//*[contains(text(),'Current FY')]");
    By projectionForNext3Years_Text = By.xpath("//*[contains(text(),'Projections for next three years')]");
    By fyEndDate_1_Text = By.xpath("//*[@id='react-project_impact-fy_end_date_1']"); // add one year
    By fyEndDate_2_Text = By.xpath("//*[@id='react-project_impact-fy_end_date_2']");
    By fyEndDate_3_Text = By.xpath("//*[@id='react-project_impact-fy_end_date_3']");
    By quantitativeImpactTile_Text = By.xpath("//*[contains(text(),'Quantitative Impact (SGD)')]");
    By overseasSales1st_Edit = By.xpath("//*[@id='react-project_impact-overseas_sales_0']");
    By overseasSales2nd_Edit = By.xpath("//*[@id='react-project_impact-overseas_sales_1']"); // Must be today or later - currentdate - 2 input
    By overseasSales3rd_Edit = By.xpath("//*[@id='react-project_impact-overseas_sales_2']"); // todays Date - not able to select
    By overseasSales4th_Edit = By.xpath("//*[@id='react-project_impact-overseas_sales_3']");

    By overseasInvestments1st_Edit = By.xpath("//input[@id='react-project_impact-overseas_investments_0']");
    By overseasInvestments2nd_Edit = By.xpath("//input[@id='react-project_impact-overseas_investments_1']");
    By overseasInvestments3rd_Edit = By.xpath("//input[@id='react-project_impact-overseas_investments_2']");
    By overseasInvestments4th_Edit = By.xpath("//input[@id='react-project_impact-overseas_investments_3']");

    By rationaleForProjections_Edit = By.xpath("//textarea[@id='react-project_impact-rationale_remarks']");
    By nonTangibleBenefits_Edit = By.xpath("//textarea[@id='react-project_impact-benefits_remarks']");
    String startDateAlertText = "Must be today or later";
    By businessImpactFormErrorNumber_Text = By.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/ul[1]/li[4]/a[1]/div[1]/span[1]");

    //*******************************
    /**
     * clickAndVerifyBusinessImpactPage - Function to clickAndVerifyBusinessImpactPage
     * @param - nothing
     * @return true or false
     */
    public boolean clickAndVerifyBusinessImpactPage()
    {
        boolean status;
        try
        {
            Thread.sleep(1000);
            BaseClass.getDriver().findElement(By.xpath("//span[contains(text(),'Business Impact')]")).click();
            Assert.assertTrue(utils.isWebElementDisplayed(businessImpactTitle_Text, 15));
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
     * enterFYEndDate - Function to enterFYEndDate
     * @param - nothing
     * @return true or false
     */
    public boolean enterFYEndDate()
    {
        boolean status;
        try
        {
            DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
            Date date = new Date();
            String currentDate= dateFormat.format(date);
            Thread.sleep(2000);
            if (props.getProperty("Headless").toUpperCase().equals("YES"))
            {
                BaseClass.getDriver().findElement(By.id("react-project_impact-fy_end_date_0")).sendKeys(currentDate);
            }
            else
            {
                Assert.assertTrue(utils.typeTextToElement(fYEndDate_Edit, currentDate));
            }
            Assert.assertTrue(utils.isWebElementDisplayed(currentFy_Text, 15));
            Assert.assertTrue(utils.isWebElementDisplayed(projectionForNext3Years_Text, 10));
            String fyEndDate_1_Date = utils.getFutureYearFromCurrent(1, "dd MMM yyyy");
            Assert.assertEquals(utils.getTextFromElement(fyEndDate_1_Text), fyEndDate_1_Date);
            String fyEndDate_2_Date = utils.getFutureYearFromCurrent(2, "dd MMM yyyy");
            Assert.assertEquals(utils.getTextFromElement(fyEndDate_2_Text), fyEndDate_2_Date);
            String fyEndDate_3_Date = utils.getFutureYearFromCurrent(3, "dd MMM yyyy");
            Assert.assertEquals(utils.getTextFromElement(fyEndDate_3_Text), fyEndDate_3_Date);
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
     * enterFYEndDate - Function to enterFYEndDate
     * @param - nothing
     * @return true or false
     */
    public boolean validateFYEndDate()
    {
        boolean status;
        try
        {
            Assert.assertTrue(utils.isWebElementDisplayed(businessImpactTitle_Text, 15));
            Assert.assertEquals(utils.getTextFromElement(businessImpactFormErrorNumber_Text), "11");
            DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
            Date date = new Date();
            String currentDate= dateFormat.format(date);
            Thread.sleep(2000);
            if (props.getProperty("Headless").toUpperCase().equals("YES"))
            {
                BaseClass.getDriver().findElement(By.id("react-project_impact-fy_end_date_0")).sendKeys(currentDate);
            }
            else
            {
                Assert.assertTrue(utils.typeTextToElement(fYEndDate_Edit, currentDate));
            }
            Assert.assertTrue(utils.isWebElementDisplayed(currentFy_Text, 15));
            Assert.assertTrue(utils.isWebElementDisplayed(projectionForNext3Years_Text, 10));
            String fyEndDate_1_Date = utils.getFutureYearFromCurrent(1, "dd MMM yyyy");
            Assert.assertEquals(utils.getTextFromElement(fyEndDate_1_Text), fyEndDate_1_Date);
            String fyEndDate_2_Date = utils.getFutureYearFromCurrent(2, "dd MMM yyyy");
            Assert.assertEquals(utils.getTextFromElement(fyEndDate_2_Text), fyEndDate_2_Date);
            String fyEndDate_3_Date = utils.getFutureYearFromCurrent(3, "dd MMM yyyy");
            Assert.assertEquals(utils.getTextFromElement(fyEndDate_3_Text), fyEndDate_3_Date);
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
     * enteringOverseasSalesDetails - Function to enteringOverseasSalesDetails
     * @param - nothing
     * @return true or false
     */
    public boolean enteringOverseasSalesDetails()
    {
        boolean status;
        try
        {
            if(utils.isWebElementDisplayed(quantitativeImpactTile_Text, 10))
            {
                Assert.assertTrue(utils.typeTextToElement(overseasSales1st_Edit,utils.getTestDataFromJSON("TD_BusinessImpactPage","overseasSales1stEdit")));
                Assert.assertTrue(utils.typeTextToElement(overseasSales2nd_Edit,utils.getTestDataFromJSON("TD_BusinessImpactPage","overseasSales2ndEdit")));
                Assert.assertTrue(utils.typeTextToElement(overseasSales3rd_Edit,utils.getTestDataFromJSON("TD_BusinessImpactPage","overseasSales3rdEdit")));
                Assert.assertTrue(utils.typeTextToElement(overseasSales4th_Edit,utils.getTestDataFromJSON("TD_BusinessImpactPage","overseasSales4thEdit")));
            }
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
     * enteringOverseasInvestmentsDetails - Function to enteringOverseasInvestmentsDetails
     * @param - nothing
     * @return true or false
     */
    public boolean enteringOverseasInvestmentsDetails()
    {
        boolean status;
        try
        {
            if(utils.isWebElementDisplayed(quantitativeImpactTile_Text, 10))
            {
                Assert.assertTrue(utils.typeTextToElement(overseasInvestments1st_Edit, utils.getTestDataFromJSON("TD_BusinessImpactPage","overseasInvestments1stEdit")));
                Assert.assertTrue(utils.typeTextToElement(overseasInvestments2nd_Edit, utils.getTestDataFromJSON("TD_BusinessImpactPage","overseasInvestments2ndEdit")));
                Assert.assertTrue(utils.typeTextToElement(overseasInvestments3rd_Edit, utils.getTestDataFromJSON("TD_BusinessImpactPage","overseasInvestments3rdEdit")));
                Assert.assertTrue(utils.typeTextToElement(overseasInvestments4th_Edit, utils.getTestDataFromJSON("TD_BusinessImpactPage","overseasInvestments4thEdit")));
            }
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
     * enteringRationaleAndNonTangibleBenefitsDetails - Function to enteringRationaleAndNonTangibleBenefitsDetails
     * @param - nothing
     * @return true or false
     */
    public boolean enteringRationaleAndNonTangibleBenefitsDetails()
    {
        boolean status;
        try
        {
            if(utils.isWebElementDisplayed(quantitativeImpactTile_Text, 10))
            {
                Assert.assertTrue(utils.typeTextToElement(rationaleForProjections_Edit, utils.getTestDataFromJSON("TD_BusinessImpactPage","rationaleForProjectionsEdit")));
                Assert.assertTrue(utils.typeTextToElement(nonTangibleBenefits_Edit,utils.getTestDataFromJSON("TD_BusinessImpactPage","rationaleForProjectionsEdit")));
                Assert.assertTrue(utils.clickElement(GlobalValues.save_Btn, 15));
                utils.isWebElementDisplayed(GlobalValues.draftSaved_Text, 10);
            }
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
