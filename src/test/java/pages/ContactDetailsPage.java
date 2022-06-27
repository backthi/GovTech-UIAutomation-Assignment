package pages;

import Utils.Utils;
import Utils.GlobalValues;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import static tests.BaseClass.driver;
import static tests.BaseClass.js;

public class ContactDetailsPage
{
    Utils utils = new Utils();
    public final Logger logger = LoggerFactory.getLogger(ContactDetailsPage.class);

    By contactDetails_Link = By.xpath("//span[contains(text(),'Contact Details')]");
    By contactDetailsPageTitle_Text = By.xpath("//*[contains(text(),'Provide Your Contact Details')]");
    By mainContactPersonName_Edit = By.xpath("//input[@id='react-contact_info-name']");
    By mainContactPersonJobTitle_Edit = By.xpath("//input[@id='react-contact_info-designation']");
    By mainContactPersonContactNo_Edit = By.xpath("//input[@id='react-contact_info-phone']");
    By mainContactPersonEmail_Edit = By.xpath("//input[@id='react-contact_info-primary_email']");
    By mainContactPersonAlternateEmail_Edit = By.xpath("//input[@id='react-contact_info-secondary_email']");
    By mailingAddressTitle_Text = By.xpath("//label[contains(text(),'Mailing Address')]");
    By letterOfOfferAddressTitle_Text = By.xpath("//h3[contains(text(),'Letter Of Offer Addressee')]");
    By mailingAddressPostalCode_Edit = By.xpath("//input[@id='react-contact_info-correspondence_address-postal']");
    By mailingAddressBlockHouseNo_Edit = By.xpath("//input[@id='react-contact_info-correspondence_address-block']");
    By mailingAddressStreet_Edit = By.xpath("//input[@id='react-contact_info-correspondence_address-street']");
    By mailingAddressLevel_Edit = By.xpath("//input[@id='react-contact_info-correspondence_address-level']");
    By mailingAddressUnit_Edit = By.xpath("//input[@id='react-contact_info-correspondence_address-unit']");
    By mailingAddressBuildingName_Edit = By.xpath("//input[@id='react-contact_info-correspondence_address-building_name']");
    By mailingAddressSameAsRegisteredAddress_ChkBox = By.xpath("//input[@id='react-contact_info-correspondence_address-copied']");

    By letterOfOfferAddresseeName_Edit = By.xpath("//input[@id='react-contact_info-offeree_name']");
    By letterOfOfferAddresseeJobTitle_Edit = By.xpath("//input[@id='react-contact_info-offeree_designation']");
    By letterOfOfferAddresseeEmail_Edit = By.xpath("//input[@id='react-contact_info-offeree_email']");
    By letterOfOfferAddresseeSameAsMainContactPerson_ChkBox = By.xpath("//input[@id='react-contact_info-copied']");

    By withoutEntering_ValidationMessage = By.xpath("//p[@id='react-contact_info-designation-alert']");  // getText() - We need a response for this field

    String warningMessageText = "We need a response for this field";

    //*******************************
    /**
     * clickAndVerifyContactDetailsPage - Function to clickAndVerifyContactDetailsPage
     * @param - nothing
     * @return true or false
     */
    public boolean clickAndVerifyContactDetailsPage()
    {
        boolean status;
        try
        {
            Thread.sleep(2000);
//            utils.clickElement(contactDetails_Link, 25);
            driver.findElement(By.xpath("//span[contains(text(),'Contact Details')]")).click();
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
     * enterMainContactPersonDetails - Function to enterMainContactPersonDetails
     * @param - nothing
     * @return true or false
     */
    public boolean enterMainContactPersonDetails()
    {
        boolean status;
        try
        {
            if(utils.isWebElementDisplayed(contactDetailsPageTitle_Text, 20))
            {
                Assert.assertTrue(utils.typeTextToElement(mainContactPersonName_Edit,utils.getTestDataFromJSON("TD_ContactDetailsPage","personName")),"Failed to type into the element: " + mainContactPersonName_Edit.toString());
                Assert.assertTrue(utils.typeTextToElement(mainContactPersonJobTitle_Edit,utils.getTestDataFromJSON("TD_ContactDetailsPage","personJobTitle")));
                Assert.assertTrue(utils.typeTextToElement(mainContactPersonContactNo_Edit,utils.getTestDataFromJSON("TD_ContactDetailsPage","personContactNo")));
                Assert.assertTrue(utils.typeTextToElement(mainContactPersonEmail_Edit,utils.getTestDataFromJSON("TD_ContactDetailsPage","personEmail")));
                Assert.assertTrue(utils.typeTextToElement(mainContactPersonAlternateEmail_Edit,utils.getTestDataFromJSON("TD_ContactDetailsPage","personAlternateEmail")));
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
     * enterMailingAddress - Function to enterMailingAddress
     * @param - nothing
     * @return true or false
     */
    public boolean enterMailingAddress()
    {
        boolean status;
        try
        {
            if(utils.isWebElementDisplayed(mailingAddressPostalCode_Edit, 20))
            {
                Assert.assertTrue(utils.typeTextToElement(mailingAddressPostalCode_Edit,utils.getTestDataFromJSON("TD_ContactDetailsPage","mailingAddressPostalCode")));
                driver.findElement(mailingAddressPostalCode_Edit).sendKeys(Keys.ENTER);
                Thread.sleep(2000);
                Assert.assertEquals(utils.getAttributeFromElement(mailingAddressBlockHouseNo_Edit, "Value"),utils.getTestDataFromJSON("TD_ContactDetailsPage","mailingAddressBlkHouseNo"));
                Assert.assertEquals(utils.getAttributeFromElement(mailingAddressStreet_Edit, "Value"),utils.getTestDataFromJSON("TD_ContactDetailsPage","mailingAddressStreetName"));
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
     * enteringMailingAddress_withSameAsCheckbox - Function to enteringMailingAddress_withSameAsCheckbox
     * @param - nothing
     * @return true or false
     */
    public boolean enteringMailingAddress_withSameAsCheckbox()
    {
        boolean status;
        try
        {
            js.executeScript("arguments[0].scrollIntoView();", driver.findElement(mainContactPersonEmail_Edit));
            if(utils.isWebElementDisplayed(mailingAddressTitle_Text, 10))
            {
                Assert.assertTrue(utils.clickElement(mailingAddressSameAsRegisteredAddress_ChkBox, 15));
                // Verify Auto-Populate
                Assert.assertEquals(utils.getAttributeFromElement(mailingAddressPostalCode_Edit, "Value"),utils.getTestDataFromJSON("TD_ContactDetailsPage","mailngAddreSameAsChkBxPostalCode"));
                Assert.assertEquals(utils.getAttributeFromElement(mailingAddressBlockHouseNo_Edit, "Value"),utils.getTestDataFromJSON("TD_ContactDetailsPage","mailngAddreSameAsChkBxBlkHouseNo"));
                Assert.assertEquals(utils.getAttributeFromElement(mailingAddressStreet_Edit, "Value"),utils.getTestDataFromJSON("TD_ContactDetailsPage","mailngAddreSameAsChkBxStreetName"));
                Assert.assertEquals(utils.getAttributeFromElement(mailingAddressLevel_Edit, "Value"),utils.getTestDataFromJSON("TD_ContactDetailsPage","mailngAddreSameAsChkBxLevel"));
                Assert.assertEquals(utils.getAttributeFromElement(mailingAddressUnit_Edit, "Value"),utils.getTestDataFromJSON("TD_ContactDetailsPage","mailngAddreSameAsChkBxUnit"));
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
     * enteringLetterOfOfferAddress - Function to enteringLetterOfOfferAddress
     * @param - nothing
     * @return true or false
     */
    public boolean enteringLetterOfOfferAddress()
    {
        boolean status;
        try
        {
            js.executeScript("arguments[0].scrollIntoView();", driver.findElement(letterOfOfferAddressTitle_Text));
            if(utils.isWebElementDisplayed(letterOfOfferAddressTitle_Text, 10))
            {
                Assert.assertTrue(utils.typeTextToElement(letterOfOfferAddresseeName_Edit, utils.getTestDataFromJSON("TD_ContactDetailsPage","letterOfOfferAddresseeName")));
                Assert.assertTrue(utils.typeTextToElement(letterOfOfferAddresseeJobTitle_Edit, utils.getTestDataFromJSON("TD_ContactDetailsPage","letterOfOfferAddresseeJobTitle")));
                Assert.assertTrue(utils.typeTextToElement(letterOfOfferAddresseeEmail_Edit, utils.getTestDataFromJSON("TD_ContactDetailsPage","letterOfOfferAddresseeEmail")));
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
     * enteringLetterOfOfferAddress_withSameAsCheckbox - Function to enteringLetterOfOfferAddress_withSameAsCheckbox
     * @param - nothing
     * @return true or false
     */
    public boolean enteringLetterOfOfferAddress_withSameAsCheckbox()
    {
        boolean status;
        try
        {
            js.executeScript("arguments[0].scrollIntoView();", driver.findElement(letterOfOfferAddressTitle_Text));
            if(utils.isWebElementDisplayed(letterOfOfferAddressTitle_Text, 10))
            {
                Assert.assertTrue(utils.clickElement(letterOfOfferAddresseeSameAsMainContactPerson_ChkBox, 15));
                // Verify Auto-Populate
                Assert.assertEquals(utils.getAttributeFromElement(letterOfOfferAddresseeName_Edit,"Value"),utils.getTestDataFromJSON("TD_ContactDetailsPage","personName"));
                Assert.assertEquals(utils.getAttributeFromElement(letterOfOfferAddresseeJobTitle_Edit,"Value"),utils.getTestDataFromJSON("TD_ContactDetailsPage","personJobTitle"));
                Assert.assertEquals(utils.getAttributeFromElement(letterOfOfferAddresseeEmail_Edit, "Value"),utils.getTestDataFromJSON("TD_ContactDetailsPage","personEmail"));
                Assert.assertTrue(utils.clickElement(GlobalValues.save_Btn, 15));
                Assert.assertTrue(utils.isWebElementDisplayed(GlobalValues.draftSaved_Text, 10));
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

    // Need to add Negative Scenarios - All Field Validations
}
