package pages;

import Utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.ArrayList;

import static tests.BaseClass.driver;
import static tests.BaseClass.js;

public class ContactDetailsPage
{
    Utils utils = new Utils();
    CommonPage commonPage = new CommonPage();

    public final Logger logger = LoggerFactory.getLogger(ContactDetailsPage.class);

    // //h2[contains(text(),'Provide Your Contact Details')] - Page
    //  Name - //input[@id='react-contact_info-name']
    // Job Title - //input[@id='react-contact_info-designation']
    // Contact NO - //input[@id='react-contact_info-phone']
    //  email -//input[@id='react-contact_info-primary_email']
    // Alternate Email - //input[@id='react-contact_info-secondary_email']

    // MailingAddress
    // Same as registered address in company profile -  //input[@id='react-contact_info-correspondence_address-copied']
    // Postal Code - //input[@id='react-contact_info-correspondence_address-postal']
    // Block/House No - //input[@id='react-contact_info-correspondence_address-block']
    // street - //input[@id='react-contact_info-correspondence_address-street']
    // Level - //input[@id='react-contact_info-correspondence_address-level']
    // Unit - //input[@id='react-contact_info-correspondence_address-unit']
    // Building Name -  //input[@id='react-contact_info-correspondence_address-building_name']

    // Letter of offer
    // Same as Main contact Person checkbox - //input[@id='react-contact_info-copied']
    // Name -  //input[@id='react-contact_info-offeree_name']
    // Job title -  //input[@id='react-contact_info-offeree_designation']
    // Email - //input[@id='react-contact_info-offeree_email']

    By contactDetails_Link = By.xpath("//span[contains(text(),'Contact Details')]");
    By contactDetailsPageTitle_Text = By.xpath("//h2[contains(text(),'Provide Your Contact Details')]");
    By mainContactPersonName_Edit = By.xpath("//input[@id='react-contact_info-name']");
    By mainContactPersonJobTitle_Edit = By.xpath("//input[@id='react-contact_info-designation']");
    By mainContactPersonContactNo_Edit = By.xpath("//input[@id='react-contact_info-phone']");
    By mainContactPersonEmail_Edit = By.xpath("//input[@id='react-contact_info-primary_email']");
    By mainContactPersonAlternateEmail_Edit = By.xpath("//input[@id='react-contact_info-secondary_email']");

    // //label[contains(text(),'Mailing Address')]
    // //h3[contains(text(),'Letter Of Offer Addressee')]
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

    public boolean clickAndVerifyContactDetailsPage()
    {
        boolean status = false;
        try
        {
            if(utils.isWebElementDisplayed(contactDetailsPageTitle_Text, 10))
            {
                logger.info("Successfully Launched ContactDetails Page");
            }
            else
            {
                Assert.assertTrue(utils.ClickElement(contactDetails_Link, 15));
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


    public boolean enterMainContactPersonDetails()
    {
        boolean status = false;
        try
        {
            if(utils.isWebElementDisplayed(contactDetailsPageTitle_Text, 10))
            {
                Assert.assertTrue(utils.TypeTextToElement(mainContactPersonName_Edit,"User1"));
                Assert.assertTrue(utils.TypeTextToElement(mainContactPersonJobTitle_Edit,"Businessman"));
                Assert.assertTrue(utils.TypeTextToElement(mainContactPersonContactNo_Edit,"82283822891"));
                Assert.assertTrue(utils.TypeTextToElement(mainContactPersonEmail_Edit,"abc@gmail.com"));
                Assert.assertTrue(utils.TypeTextToElement(mainContactPersonAlternateEmail_Edit,"xyz@gmail.com"));
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

    public boolean enterMailingAddress()
    {
        boolean status = false;
        try
        {
            if(utils.isWebElementDisplayed(mailingAddressPostalCode_Edit, 10))
            {
                Assert.assertTrue(utils.TypeTextToElement(mailingAddressPostalCode_Edit,"310145"));
                driver.findElement(mailingAddressPostalCode_Edit).sendKeys(Keys.TAB);
                Thread.sleep(2000);
                Assert.assertEquals(utils.GetAttributeFromElement(mailingAddressBlockHouseNo_Edit, "Value"),"145");
                Assert.assertEquals(utils.GetAttributeFromElement(mailingAddressStreet_Edit, "Value"),"LORONG 2 TOA PAYOH");
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

    public boolean enteringMailingAddress_withSameAsCheckbox()
    {
        boolean status = false;
        try
        {
            js.executeScript("arguments[0].scrollIntoView();", driver.findElement(mainContactPersonEmail_Edit));
            if(utils.isWebElementDisplayed(mailingAddressTitle_Text, 10))
            {
                Assert.assertTrue(utils.ClickElement(mailingAddressSameAsRegisteredAddress_ChkBox, 15));
                // Verify Auto-Populate
                Assert.assertEquals(utils.GetAttributeFromElement(mailingAddressPostalCode_Edit, "Value"),"650320");
                Assert.assertEquals(utils.GetAttributeFromElement(mailingAddressBlockHouseNo_Edit, "Value"),"320");
                Assert.assertEquals(utils.GetAttributeFromElement(mailingAddressStreet_Edit, "Value"),"BUKIT BATOK STREET 33");
                Assert.assertEquals(utils.GetAttributeFromElement(mailingAddressLevel_Edit, "Value"),"03");
                Assert.assertEquals(utils.GetAttributeFromElement(mailingAddressUnit_Edit, "Value"),"33");
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

    public boolean enteringLetterOfOfferAddress()
    {
        boolean status = false;
        try
        {
            js.executeScript("arguments[0].scrollIntoView();", driver.findElement(letterOfOfferAddressTitle_Text));
            if(utils.isWebElementDisplayed(letterOfOfferAddressTitle_Text, 10))
            {
                Assert.assertTrue(utils.TypeTextToElement(letterOfOfferAddresseeName_Edit,"Person1"));
                Assert.assertTrue(utils.TypeTextToElement(letterOfOfferAddresseeJobTitle_Edit,"Construction"));
                Assert.assertTrue(utils.TypeTextToElement(letterOfOfferAddresseeEmail_Edit,"asd@gmail.com"));
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

    public boolean enteringLetterOfOfferAddress_withSameAsCheckbox()
    {
        boolean status = false;
        try
        {
            js.executeScript("arguments[0].scrollIntoView();", driver.findElement(letterOfOfferAddressTitle_Text));
            if(utils.isWebElementDisplayed(letterOfOfferAddressTitle_Text, 10))
            {
                Assert.assertTrue(utils.ClickElement(letterOfOfferAddresseeSameAsMainContactPerson_ChkBox, 15));
                // Verify Auto-Populate
                Assert.assertEquals(utils.GetAttributeFromElement(letterOfOfferAddresseeName_Edit,"Value"),"User1");
                Assert.assertEquals(utils.GetAttributeFromElement(letterOfOfferAddresseeJobTitle_Edit,"Value"),"Businessman");
                Assert.assertEquals(utils.GetAttributeFromElement(letterOfOfferAddresseeEmail_Edit, "Value"),"abc@gmail.com");
                Assert.assertTrue(utils.ClickElement(commonPage.save_Btn, 15));
                Assert.assertTrue(utils.isWebElementDisplayed(commonPage.draftSaved_Text, 10));
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
}
