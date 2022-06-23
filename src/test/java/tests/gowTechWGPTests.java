package tests;

import Utils.Utils;
import org.testng.annotations.Test;
import pages.*;

import java.util.Properties;

public class gowTechWGPTests extends BaseClass
{
    LoginPage loginPage = new LoginPage();
    EligibilityPage eligibilityPage = new EligibilityPage();
    ContactDetailsPage contactDetailsPage = new ContactDetailsPage();
    BusinessImpactPage businessImpactPage = new BusinessImpactPage();
    ProposalPage proposalPage = new ProposalPage();
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
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }

    @Test (dependsOnMethods = { "verifyLogin" }, testName = "Filling Data on Eligibility Form", priority=1)
    public void fillingEligibilityForm()
    {
        try
        {
            eligibilityPage.checkYourEligibility();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }

    @Test (dependsOnMethods = { "verifyLogin" },testName = "Filling Data on ContactDetails Form",priority=2)
    public void fillingContactDetailsForm()
    {
        try
        {
            contactDetailsPage.clickAndVerifyContactDetailsPage();
            contactDetailsPage.enterMainContactPersonDetails();
            contactDetailsPage.enterMailingAddress();
            contactDetailsPage.enteringMailingAddress_withSameAsCheckbox();
            contactDetailsPage.enteringLetterOfOfferAddress();
            contactDetailsPage.enteringLetterOfOfferAddress_withSameAsCheckbox();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }

    @Test (dependsOnMethods = { "verifyLogin" }, testName = "fillingBusinessImpactForm",priority=3)
    public void fillingBusinessImpactForm()
    {
        try
        {
            businessImpactPage.clickAndVerifyBusinessImpactPage();
            businessImpactPage.enterFYEndDate();
            businessImpactPage.enteringOverseasSalesDetails();
            businessImpactPage.enteringOverseasInvestmentsDetails();
            businessImpactPage.enteringRationaleAndNonTangibleBenefitsDetails();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }

    @Test (dependsOnMethods = { "verifyLogin" }, testName = "fillingProposalForm",priority=4)
    public void fillingProposalForm()
    {
        try
        {
            proposalPage.clickAndVerifyProposalPage();
            proposalPage.enterProposalDetails();
            proposalPage.verifyDates();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }
}
