package pages;

import Utils.Utils;
import Utils.GlobalValues;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import tests.BaseClass;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

import static tests.BaseClass.*;

public class EligibilityPage
{
    Utils utils = new Utils();

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
    By firstQuestionWarningMessage_Text = By.xpath("//body[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[5]/div[1]/span[1]");
    By secondQuestionWarningMessage_Text = By.xpath("//body[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[7]/div[1]/span[1]");
    By thirdQuestionWarningMessage_Text = By.xpath("//body[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[9]/div[1]/span[1]");
    By fourthQuestionWarningMessage_Text = By.xpath("//body[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[11]/div[1]/span[1]");
    By fifthQuestionWarningMessage_Text = By.xpath("//body[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[13]/div[1]/span[1]");
    By FAQ_Link = By.xpath("//a[contains(text(),'FAQ')]");
    String warningMessageText = "The applicant may not meet the eligibility criteria for this grant. Visit FAQ page for more information on other government grants.";

    //*******************************
    /**
     * loginPortal - Function to login into a given portal
     * @param - nothing
     * @return true or false
     */
    public boolean fillingEligibility()
    {
        boolean status;
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        try
        {
            if(utils.isWebElementDisplayed(checkYourAvailability_Text, 25))
            {
                Thread.sleep(2000);
                Assert.assertTrue(utils.clickElement(firstQuestion_No_RadioBtn,15));
                Assert.assertEquals(utils.getTextFromElement(firstQuestionWarningMessage_Text),utils.getTestDataFromJSON("TD_EligibilityPage","warningMessageText"));
                String currentPageHandle = BaseClass.getDriver().getWindowHandle();
                Assert.assertTrue(utils.clickElement(FAQ_Link, 10));
                ArrayList<String> tabHandles = new ArrayList<String>(BaseClass.getDriver().getWindowHandles());

                String pageTitle = utils.getTestDataFromJSON("TD_EligibilityPage","FAQPageTitle");
                boolean myNewTabFound = false;
                Properties props = utils.readConfigFile();
                if (props.getProperty("Browser").equalsIgnoreCase("Chrome"))
                {
                    for(String eachHandle : tabHandles)
                    {
                        BaseClass.getDriver().switchTo().window(eachHandle);
                        if(BaseClass.getDriver().getTitle().equalsIgnoreCase(pageTitle)) // Check the Page Title
                        {
                            BaseClass.getDriver().close();
                            BaseClass.getDriver().switchTo().window(currentPageHandle);
                            myNewTabFound = true;
                        }
                    }
                }
                else
                {
                    Thread.sleep(2000);
                    String parent= BaseClass.getDriver().getWindowHandle();
                    WebDriverWait wait = new WebDriverWait(BaseClass.getDriver(),5);
                    wait.until(ExpectedConditions.numberOfWindowsToBe(2));
                    Set<String> s1= BaseClass.getDriver().getWindowHandles();
                    for(String s2:s1)
                    {
                        if(!currentPageHandle.equalsIgnoreCase(s2))
                        {
                            BaseClass.getDriver().switchTo().window(s2);
                            Thread.sleep(5000);
                            System.out.println(BaseClass.getDriver().getWindowHandle());
                            System.out.println("get title of window: "+BaseClass.getDriver().getTitle());
                            if(BaseClass.getDriver().getTitle().equalsIgnoreCase(pageTitle)) // Check the Page Title
                            {
                                BaseClass.getDriver().close();
                                BaseClass.getDriver().switchTo().window(parent);
                            }
                        }
                    }
                    BaseClass.getDriver().switchTo().window(currentPageHandle);
                    myNewTabFound = true;
                }
                if(myNewTabFound)
                {
                    logger.info("FAQ Page on New Tab is verified successfully");
                }
                Thread.sleep(1000);
                Assert.assertTrue(utils.clickElement(secondQuestion_No_RadioBtn,15));
                Assert.assertEquals(utils.getTextFromElement(secondQuestionWarningMessage_Text),warningMessageText);
                Assert.assertTrue(utils.clickElement(thirdQuestion_No_RadioBtn,15));
                Assert.assertEquals(utils.getTextFromElement(thirdQuestionWarningMessage_Text),warningMessageText);
                Assert.assertTrue(utils.clickElement(fourthQuestion_No_RadioBtn,15));
                Assert.assertEquals(utils.getTextFromElement(fourthQuestionWarningMessage_Text),warningMessageText);
                Assert.assertTrue(utils.clickElement(fifthQuestion_No_RadioBtn,15));
                Assert.assertEquals(utils.getTextFromElement(fifthQuestionWarningMessage_Text),warningMessageText);
                js.executeScript("arguments[0].scrollIntoView();", BaseClass.getDriver().findElement(checkYourAvailability_Text));
                Assert.assertTrue(utils.clickElement(secondQuestion_Yes_RadioBtn,15));
                Assert.assertTrue(utils.clickElement(thirdQuestion_Yes_RadioBtn,15));
                Assert.assertTrue(utils.clickElement(fourthQuestion_Yes_RadioBtn,15));
                Assert.assertTrue(utils.clickElement(fifthQuestion_Yes_RadioBtn,15));
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
