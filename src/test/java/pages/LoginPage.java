package pages;

import Utils.Utils;
import org.openqa.selenium.By;
import org.testng.Assert;
import tests.BaseClass;

import java.util.Properties;

public class LoginPage
{
    Utils utils = new Utils();

    By userName_Edit = By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[2]/div[2]/div/div/form/div[1]/input");

    By password_Edit = By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[2]/div[2]/div/div/form/div[2]/input");

    By signIN_Btn =By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[2]/div[2]/div/div/form/input[3]");

    By bgpLoginPageTitle_Image = By.xpath("//img[@class='bgp-logo-desktop']");
    By bgpLoginPageLogin_Btn = By.id("login-button");
    By corpPassManualLoginEntityID_Edit = By.id("entityId");
    By corpPassManualLoginUserID_Edit = By.id("userId");
    By corpPassManualLoginUseRole_Edit = By.id("userRole");
    By corpPassManualLoginUseFullName_Edit = By.id("userFullName");
    By corpPassManualLogin_Btn = By.xpath("//button[@id='btn001']");
    By myGrantsPageTitle_Text = By.xpath("//*[text()='my Grants']");
    By getNewGrants_Link = By.xpath("//*[text()='Get new grant']");
    By getNewGrantsSectors_Link = By.xpath("//*[text()='IT']");
    By bringMyBusinessOverseas_Link = By.xpath("//*[text()='Bring my business overseas or establish a stronger international presence']");
    By marketReadinessAssistance_Link = By.xpath("//*[text()='Market Readiness Assistance']");
    By marketReadinessAssistanceApply_Btn = By.id("go-to-grant");
    By grantActionsProceed_Btn = By.id("keyPage-form-button");


    public boolean loginPortal()
    {
        boolean status;
        Properties prop = utils.readConfigFile();
        try
        {
            BaseClass.driver.get(prop.getProperty("Url"));
            Thread.sleep(3000);
            if(utils.isWebElementDisplayed(userName_Edit, 10))
            {
                Assert.assertTrue(utils.ClickElement(userName_Edit,15));
                Assert.assertTrue(utils.TypeTextToElement(userName_Edit,"public"));
                Assert.assertTrue(utils.TypeTextToElement(password_Edit,"Let$BeC001"));
                Assert.assertTrue(utils.ClickElement(signIN_Btn, 15));
                //BGP Login Page
                Assert.assertTrue(utils.isWebElementDisplayed(bgpLoginPageTitle_Image, 10));
                Assert.assertTrue(utils.ClickElement(bgpLoginPageLogin_Btn, 15));
                // CorpPass Page
                Assert.assertTrue(utils.TypeTextToElement(corpPassManualLoginEntityID_Edit,"BGPQEDEMO"));
                Assert.assertTrue(utils.TypeTextToElement(corpPassManualLoginUserID_Edit,"S1234567A"));
                Assert.assertTrue(utils.TypeTextToElement(corpPassManualLoginUseRole_Edit,"Acceptor"));
                Assert.assertTrue(utils.TypeTextToElement(corpPassManualLoginUseFullName_Edit,"Tan Ah Kow"));
                Assert.assertTrue(utils.ClickElement(corpPassManualLogin_Btn,10));
                // My Grants Page
                Assert.assertTrue(utils.isWebElementDisplayed(myGrantsPageTitle_Text,20));
                Assert.assertTrue(utils.ClickElement(getNewGrants_Link, 10));
                Assert.assertTrue(utils.ClickElement(getNewGrantsSectors_Link, 10));
                Assert.assertTrue(utils.ClickElement(bringMyBusinessOverseas_Link, 10));
                Assert.assertTrue(utils.ClickElement(marketReadinessAssistance_Link, 10));
                Assert.assertTrue(utils.ClickElement(marketReadinessAssistanceApply_Btn, 10));
                Assert.assertTrue(utils.ClickElement(grantActionsProceed_Btn, 10));
            }
            else if (utils.isWebElementDisplayed(bgpLoginPageLogin_Btn, 10))
            {
                //BGP Login Page
                Assert.assertTrue(utils.isWebElementDisplayed(bgpLoginPageTitle_Image, 10));
                Assert.assertTrue(utils.ClickElement(bgpLoginPageLogin_Btn, 15));
                // CorpPass Page
                Assert.assertTrue(utils.TypeTextToElement(corpPassManualLoginEntityID_Edit,"BGPQEDEMO"));
                Assert.assertTrue(utils.TypeTextToElement(corpPassManualLoginUserID_Edit,"S1234567A"));
                Assert.assertTrue(utils.TypeTextToElement(corpPassManualLoginUseRole_Edit,"Acceptor"));
                Assert.assertTrue(utils.TypeTextToElement(corpPassManualLoginUseFullName_Edit,"Tan Ah Kow"));
                Assert.assertTrue(utils.ClickElement(corpPassManualLogin_Btn,10));
                // My Grants Page
                Assert.assertTrue(utils.isWebElementDisplayed(myGrantsPageTitle_Text,20));
                Assert.assertTrue(utils.ClickElement(getNewGrants_Link, 10));
                Assert.assertTrue(utils.ClickElement(getNewGrantsSectors_Link, 10));
                Assert.assertTrue(utils.ClickElement(bringMyBusinessOverseas_Link, 10));
                Assert.assertTrue(utils.ClickElement(marketReadinessAssistance_Link, 10));
                Assert.assertTrue(utils.ClickElement(marketReadinessAssistanceApply_Btn, 10));
                Assert.assertTrue(utils.ClickElement(grantActionsProceed_Btn, 10));
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
