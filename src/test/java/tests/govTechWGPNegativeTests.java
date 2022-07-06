package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.lang.reflect.Method;

import static Utils.extentreports.ExtentTestManager.startTest;

public class govTechWGPNegativeTests extends BaseClass
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
        startTest("NegativeTest: " + method.getName(), "Verify Login is successfull");
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
    public void fillingEligibilityForm(Method method)
    {
        startTest("NegativeTest: " + method.getName(), "Filling Data on Eligibility Form");
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
        startTest("NegativeTest: " + method.getName(), "Filling Data on ContactDetails Form");
        try
        {
            Assert.assertTrue(contactDetailsPage.clickAndVerifyContactDetailsPage());
            Assert.assertTrue(contactDetailsPage.validateMainContactPersonDetails());
            Assert.assertTrue(contactDetailsPage.validateMailingAddress());
            Assert.assertTrue(contactDetailsPage.enteringMailingAddress_withSameAsCheckbox());
            Assert.assertTrue(contactDetailsPage.validateLetterOfOfferAddress());
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
    public void fillingProposalForm(Method method)
    {
        startTest("NegativeTest: " + method.getName(), "filling data on Proposal Form");
        try
        {
            Assert.assertTrue(proposalPage.clickAndVerifyProposalPage());
            Assert.assertTrue(proposalPage.validateProposalDetails());
            Assert.assertTrue(proposalPage.verifyDates());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
            e.getMessage();
        }
    }

    @Test (dependsOnMethods = { "verifyLogin" }, testName = "fillingCostForm",priority=4)
    public void fillingCostForm(Method method)
    {
        startTest("NegativeTest: " + method.getName(), "filling details on Cost Form");
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

    @Test (dependsOnMethods = { "verifyLogin" }, testName = "fillingDeclareAndReviewForm",priority=5)
    public void fillingDeclareAndReviewForm(Method method)
    {
        startTest("NegativeTest: " + method.getName(), "filling Details on Declare And Review Form");
        try
        {
            Assert.assertTrue(declareAndReviewPage.clickAndVerifyDeclareAndVerify());
            Assert.assertTrue(declareAndReviewPage.fillingAllQuestionsWithOtherOptions());
            Assert.assertTrue(declareAndReviewPage.clickOnReview());
            Assert.assertTrue(proposalPage.enterStartDateAfterReview());

            Assert.assertTrue(businessImpactPage.validateFYEndDate());
            Assert.assertTrue(businessImpactPage.enteringOverseasSalesDetails());
            Assert.assertTrue(businessImpactPage.enteringOverseasInvestmentsDetails());
            Assert.assertTrue(businessImpactPage.enteringRationaleAndNonTangibleBenefitsDetails());

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
