package pages;

import Utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import tests.BaseClass;

import java.util.ArrayList;
import java.util.Properties;

import static tests.BaseClass.driver;
import static tests.BaseClass.js;

public class EligibilityPage
{
    Utils utils = new Utils();
    CommonPage commonPage = new CommonPage();

    public final Logger logger = LoggerFactory.getLogger(EligibilityPage.class);

    By checkYourAvailability_Text = By.xpath("//*[contains(text(),'Check Your Eligibility')]");

    By firstQuestion_Yes_RadioBtn = By.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[5]/div[1]/div[2]/label[1]/span[1]");
    By secondQuestion_Yes_RadioBtn = By.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[6]/div[1]/div[2]/label[1]/span[1]");

    By thirdQuestion_Yes_RadioBtn = By.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[7]/div[1]/div[2]/label[1]/span[1]");
    By fourthQuestion_Yes_RadioBtn = By.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[8]/div[1]/div[2]/label[1]/span[1]");
    By fifthQuestion_Yes_RadioBtn = By.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[9]/div[1]/div[2]/label[1]/span[1]");
    // No RadioButtons for all Questions
    By firstQuestion_No_RadioBtn = By.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[4]/div[1]/div[2]/label[2]/span[1]");
    By secondQuestion_No_RadioBtn = By.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[6]/div[1]/div[2]/label[2]/span[1]");
    By thirdQuestion_No_RadioBtn = By.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[8]/div[1]/div[2]/label[2]/span[1]");

    By fourthQuestion_No_RadioBtn = By.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[10]/div[1]/div[2]/label[2]/span[1]");

    By fifthQuestion_No_RadioBtn = By.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[12]/div[1]/div[2]/label[2]/span[1]");

//    By next_Btn = By.xpath("//button[@id='next-btn']");
//    By save_Btn = By.xpath("//button[@id='save-btn']");
    By firstQuestionWarningMessage_Text = By.xpath("//body[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[5]/div[1]/span[1]");
    By secondQuestionWarningMessage_Text = By.xpath("//body[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[7]/div[1]/span[1]");
    By thirdQuestionWarningMessage_Text = By.xpath("//body[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[9]/div[1]/span[1]");
    By fourthQuestionWarningMessage_Text = By.xpath("//body[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[11]/div[1]/span[1]");
    By fifthQuestionWarningMessage_Text = By.xpath("//body[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[13]/div[1]/span[1]");
    By FAQ_Link = By.xpath("//a[contains(text(),'FAQ')]");
//    By draftSaved_Text = By.xpath("//div[contains(text(),'Draft Saved')]");

    String warningMessageText = "The applicant may not meet the eligibility criteria for this grant. Visit FAQ page for more information on other government grants.";
    public boolean checkYourEligibility()
    {
        boolean status = false;
        try
        {
            if(utils.isWebElementDisplayed(checkYourAvailability_Text, 10))
            {
                Assert.assertTrue(utils.ClickElement(firstQuestion_No_RadioBtn,15));
                Assert.assertEquals(utils.GetTextFromElement(firstQuestionWarningMessage_Text),warningMessageText);
                String currentPageHandle = driver.getWindowHandle();
                Assert.assertTrue(utils.ClickElement(FAQ_Link, 10));
                ArrayList<String> tabHandles = new ArrayList<String>(driver.getWindowHandles());

                String pageTitle = "About";
                boolean myNewTabFound = false;

                for(String eachHandle : tabHandles)
                {
                    driver.switchTo().window(eachHandle);
                    // Check Your Page Title
                    if(driver.getTitle().equalsIgnoreCase(pageTitle))
                    {
                        //Close the current tab
                        driver.close(); // Note driver.quit() will close all tabs

                        //Switch to Old tab
                        driver.switchTo().window(currentPageHandle);
                        myNewTabFound = true;
                    }
                }
                if(myNewTabFound)
                {
                    logger.info("FAQ Page on New Tab is verified successfully");
                }
                Assert.assertTrue(utils.ClickElement(secondQuestion_No_RadioBtn,15));
                Assert.assertEquals(utils.GetTextFromElement(secondQuestionWarningMessage_Text),warningMessageText);
                Assert.assertTrue(utils.ClickElement(thirdQuestion_No_RadioBtn,15));
                Assert.assertEquals(utils.GetTextFromElement(thirdQuestionWarningMessage_Text),warningMessageText);
                Assert.assertTrue(utils.ClickElement(fourthQuestion_No_RadioBtn,15));
                Assert.assertEquals(utils.GetTextFromElement(fourthQuestionWarningMessage_Text),warningMessageText);
                Assert.assertTrue(utils.ClickElement(fifthQuestion_No_RadioBtn,15));
                Assert.assertEquals(utils.GetTextFromElement(fifthQuestionWarningMessage_Text),warningMessageText);
                js.executeScript("arguments[0].scrollIntoView();", driver.findElement(checkYourAvailability_Text));
                Assert.assertTrue(utils.ClickElement(secondQuestion_Yes_RadioBtn,15));
                Assert.assertTrue(utils.ClickElement(thirdQuestion_Yes_RadioBtn,15));
                Assert.assertTrue(utils.ClickElement(fourthQuestion_Yes_RadioBtn,15));
                Assert.assertTrue(utils.ClickElement(fifthQuestion_Yes_RadioBtn,15));
                Assert.assertTrue(utils.ClickElement(commonPage.save_Btn,15));
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
