package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.lang.reflect.Method;

import static Utils.extentreports.ExtentTestManager.startTest;

public class govTechWGPTests extends BaseClass
{
    LoginPage loginPage = new LoginPage();
    EligibilityPage eligibilityPage = new EligibilityPage();
    ContactDetailsPage contactDetailsPage = new ContactDetailsPage();
    BusinessImpactPage businessImpactPage = new BusinessImpactPage();
    ProposalPage proposalPage = new ProposalPage();
    CostPage costPage = new CostPage();
    DeclareAndReviewPage declareAndReviewPage = new DeclareAndReviewPage();


    @Test (testName = "Login to BGP Portal", priority=0)
    public void verifyLogin(Method method)
    {
        startTest(BaseClass.getDriver().toString().split(":")[0] + ": " + method.getName(), "verifyLogin");
        try
        {
            Assert.assertTrue(loginPage.loginPortal());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
            e.getMessage();
        }
    }

    @Test (dependsOnMethods = { "verifyLogin" }, testName = "Filling Data on Eligibility Form", priority=1)
    public void fillingEligibilityForm(Method method)
    {
        startTest(BaseClass.getDriver().toString().split(":")[0] + ": " + method.getName(), "Filling Data on Eligibility Form");
        try
        {
            Assert.assertTrue(eligibilityPage.fillingEligibility());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
            e.getMessage();
        }
    }

    @Test (dependsOnMethods = { "verifyLogin" },testName = "Filling Data on ContactDetails Form",priority=2)
    public void fillingContactDetailsForm(Method method)
    {
        startTest(BaseClass.getDriver().toString().split(":")[0] + ": " + method.getName(), "Filling Data on ContactDetails Form");
        try
        {
            Assert.assertTrue(contactDetailsPage.clickAndVerifyContactDetailsPage());
            Assert.assertTrue(contactDetailsPage.enterMainContactPersonDetails());
            Assert.assertTrue(contactDetailsPage.enteringMailingAddress_withSameAsCheckbox());
            Assert.assertTrue(contactDetailsPage.enteringLetterOfOfferAddress());
            Assert.assertTrue(contactDetailsPage.enteringLetterOfOfferAddress_withSameAsCheckbox());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
            e.getMessage();
        }
    }

    @Test (dependsOnMethods = { "verifyLogin" }, testName = "filling Data on Proposal Form",priority=3)
    public void fillingProposalForm(Method method)
    {
        startTest(BaseClass.getDriver().toString().split(":")[0] + ": " + method.getName(), "filling Data on Proposal Form");
        try
        {
            Assert.assertTrue(proposalPage.clickAndVerifyProposalPage());
            Assert.assertTrue(proposalPage.enterProposalDetails());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
            e.getMessage();
        }
    }

    @Test (dependsOnMethods = { "verifyLogin" }, testName = "filling details on BusinessImpact Form",priority=4)
    public void fillingBusinessImpactForm(Method method)
    {
        startTest(BaseClass.getDriver().toString().split(":")[0] + ": " + method.getName(), "filling details on BusinessImpact Form");
        try
        {
            Assert.assertTrue(businessImpactPage.clickAndVerifyBusinessImpactPage());
            Assert.assertTrue(businessImpactPage.enterFYEndDate());
            Assert.assertTrue(businessImpactPage.enteringOverseasSalesDetails());
            Assert.assertTrue(businessImpactPage.enteringOverseasInvestmentsDetails());
            Assert.assertTrue(businessImpactPage.enteringRationaleAndNonTangibleBenefitsDetails());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
            e.getMessage();
        }
    }

    @Test (dependsOnMethods = { "verifyLogin" }, testName = "filling details on Cost Form",priority=5)
    public void fillingCostForm(Method method)
    {
        startTest(BaseClass.getDriver().toString().split(":")[0] + ": " + method.getName(), "filling details on Cost Form");
        try
        {
            Assert.assertTrue(costPage.clickAndVerifyCostPage());
            Assert.assertTrue(costPage.enterThirdPartyVendorsDetails());
            Assert.assertTrue(costPage.enteringOfficeSpaceRentalDetails());
            Assert.assertTrue(costPage.enteringSalaryDetails());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
            e.getMessage();
        }
    }

    @Test (dependsOnMethods = { "verifyLogin" }, testName = "filling details on Declare And Review Form",priority=6)
    public void fillingDeclareAndReviewForm(Method method)
    {
        startTest(BaseClass.getDriver().toString().split(":")[0] + ": " + method.getName(), "filling details on Declare And Review Form");
        try
        {
            Assert.assertTrue(declareAndReviewPage.clickAndVerifyDeclareAndVerify());
            Assert.assertTrue(declareAndReviewPage.fillingAllQuestions());
            Assert.assertTrue(declareAndReviewPage.clickOnReview());
            Assert.assertTrue(declareAndReviewPage.reviewAllData());
            Assert.assertTrue(declareAndReviewPage.verifyApplicationSubmitted());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
            e.getMessage();
        }
    }
}
