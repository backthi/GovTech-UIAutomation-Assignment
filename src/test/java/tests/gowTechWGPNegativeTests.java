package tests;

import Utils.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.util.Properties;

public class gowTechWGPNegativeTests extends BaseClass
{
    LoginPage loginPage = new LoginPage();
    EligibilityPage eligibilityPage = new EligibilityPage();
    ContactDetailsPage contactDetailsPage = new ContactDetailsPage();
    BusinessImpactPage businessImpactPage = new BusinessImpactPage();
    ProposalPage proposalPage = new ProposalPage();
    CostPage costPage = new CostPage();
    DeclareAndReviewPage declareAndReviewPage = new DeclareAndReviewPage();
    Utils utils = new Utils();
    Properties props = utils.readConfigFile();

    @Test (testName = "Login to BGP Portal", priority=0)
    public void verifyLogin()
    {
        try
        {
            loginPage.loginPortal();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
            e.getMessage();
        }
    }

    @Test (dependsOnMethods = { "verifyLogin" }, testName = "Filling Data on Eligibility Form", priority=1)
    public void fillingEligibilityForm()
    {
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
    public void fillingContactDetailsForm()
    {
        try
        {
            Assert.assertTrue(contactDetailsPage.clickAndVerifyContactDetailsPage());
            Assert.assertTrue(contactDetailsPage.enterMainContactPersonDetails());
            Assert.assertTrue(contactDetailsPage.enterMailingAddress());
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

    @Test (dependsOnMethods = { "verifyLogin" }, testName = "fillingProposalForm",priority=3)
    public void fillingProposalForm()
    {
        try
        {
            Assert.assertTrue(proposalPage.clickAndVerifyProposalPage());
            Assert.assertTrue(proposalPage.enterProposalDetails());
            Assert.assertTrue(proposalPage.verifyDates());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
            e.getMessage();
        }
    }

    @Test (dependsOnMethods = { "verifyLogin" }, testName = "fillingBusinessImpactForm",priority=4)
    public void fillingBusinessImpactForm()
    {
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

    @Test (dependsOnMethods = { "verifyLogin" }, testName = "fillingCostForm",priority=5)
    public void fillingCostForm()
    {
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

    @Test (dependsOnMethods = { "verifyLogin" }, testName = "fillingDeclareAndReviewForm",priority=6)
    public void fillingDeclareAndReviewForm()
    {
        try
        {
            Assert.assertTrue(declareAndReviewPage.clickAndVerifyDeclareAndVerify());
            Assert.assertTrue(declareAndReviewPage.fillingAllQuestionsWithOtherOptions());
            Assert.assertTrue(declareAndReviewPage.clickOnReview());
            Assert.assertTrue(proposalPage.enterStartDateAfterReview());
            Assert.assertTrue(declareAndReviewPage.clickOnDeclareAndReviewAndReview());
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
