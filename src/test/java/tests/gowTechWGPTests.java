package tests;

import Utils.Utils;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.ContactDetailsPage;
import pages.EligibilityPage;
import pages.LoginPage;
import pages.Post;

import java.util.Properties;

public class gowTechWGPTests extends BaseClass
{
    LoginPage loginPage = new LoginPage();
    EligibilityPage eligibilityPage = new EligibilityPage();
    ContactDetailsPage contactDetailsPage = new ContactDetailsPage();
    Utils utils = new Utils();
    Properties props = utils.readConfigFile();
    Post post = new Post();

    @Test (testName = "Login to Reddit.com")
    public void verifyLogin()
    {
        try
        {
            loginPage.loginPortal();
//            By firstQuestion_No_RadioBtn = By.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[4]/div[1]/div[2]/label[2]/span[1]");
//            driver.findElement(firstQuestion_No_RadioBtn).click();
//            org.openqa.selenium.By firstQuestionWarningMessage_Text = org.openqa.selenium.By.xpath("//body[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[5]/div[1]/span[1]");
//            String txt = driver.findElement(firstQuestionWarningMessage_Text).getText();
//            System.out.println(txt);

            eligibilityPage.checkYourEligibility();
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

//    @Test (dependsOnMethods = { "verifyLogin" }, testName = "CountingSubscribedSubReddits")
//    public void VerifySubscribedSubReddits()
//    {
//        try
//        {
//            Assert.assertTrue(utils.ClickElement("SubReddit","xpath#community", 20));
//            Thread.sleep(2000); //takes time to display
//            List<WebElement> communities = utils.getWebElements("SubReddit","xpath#MyCommunities");
//            Assert.assertEquals(communities.size(),17);
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//            System.out.println(e.getCause());
//            System.out.println(e.getMessage());
//        }
//    }
//
//    @Test (dependsOnMethods = { "VerifySubscribedSubReddits" }, testName = "Verify ViewAnyOneSubReddit")
//    public void verifyViewAnyOneSubReddit()
//    {
//        try
//        {
//            Assert.assertTrue(utils.isWebElementDisplayed("SubReddit","xpath#community", 15));
//            driver.get(props.getProperty("Url"));
//            Assert.assertTrue(utils.isWebElementDisplayed("SubReddit","xpath#subscribedReddit", 15));
//            // Verifing "MavericksDnD" subReddit exists in the list
//            String str_ExpURL = ExtractValueFromJSONFile("TD_RedditApp", "redditURL");
//            Assert.assertEquals(driver.getCurrentUrl(), str_ExpURL);
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//            System.out.println(e.getCause());
//            System.out.println(e.getMessage());
//        }
//    }
//
//    @Test (dependsOnMethods = { "verifyViewAnyOneSubReddit" }, testName = "VerifyPost")
//    public void verifyPost()
//    {
//        try
//        {
//            Assert.assertTrue(utils.isWebElementDisplayed("Post","xpath#community", 15));
//            driver.get(props.getProperty("Url"));
//            Assert.assertTrue(utils.isWebElementDisplayed("Post","xpath#subscribedReddit", 15));
//            String str_ExpURL = ExtractValueFromJSONFile("TD_RedditApp", "redditURL");
//            Assert.assertEquals(driver.getCurrentUrl(), str_ExpURL);
//            String createdPost = post.createPost();
////            Thread.sleep(1000); //Given for Viewing Created Post
//            Assert.assertTrue(utils.isWebElementDisplayed("Post","classname#createdPost", 15));
//            Assert.assertEquals(utils.GetTextFromElement("Post","classname#createdPost"),createdPost);
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//            System.out.println(e.getCause());
//            System.out.println(e.getMessage());
//        }
//    }
//
//
//    @Test (dependsOnMethods = { "verifyPost" }, testName = "VerifyUpVote")
//    public void verifyUpVote()
//    {
//        try
//        {
//            Assert.assertTrue(utils.isWebElementDisplayed("Post","xpath#community", 15));
//            driver.get(props.getProperty("Url"));
//            Assert.assertTrue(utils.isWebElementDisplayed("Post","xpath#subscribedReddit", 15));
//            String str_ExpURL = ExtractValueFromJSONFile("TD_RedditApp", "redditURL");
//            Assert.assertEquals(driver.getCurrentUrl(), str_ExpURL);
//            String createdPost = post.createPost();
//            Assert.assertTrue(utils.isWebElementDisplayed("Post","classname#createdPost", 15));
//            Assert.assertEquals(utils.GetTextFromElement("Post","classname#createdPost"),createdPost);
//            Assert.assertTrue(post.downVote()); // Pressing Down Vote arrow
//            Thread.sleep(2000); //Given for Viewing While clicking Arrows
//            Assert.assertTrue(post.upVote()); // Pressing Up Vote arrow to match the count
//            String upVote = utils.getWebElement("Post","xpath#upVote",15).getAttribute("aria-pressed");
//            Assert.assertEquals(upVote,"true");
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//            System.out.println(e.getCause());
//            System.out.println(e.getMessage());
//        }
//    }
//
//    @Test (dependsOnMethods = { "verifyUpVote" }, testName = "VerifyDownVote")
//    public void verifyDownVote()
//    {
//        try
//        {
//            Assert.assertTrue(utils.isWebElementDisplayed("Post", "xpath#community", 15));
//            driver.get(props.getProperty("Url"));
//            Assert.assertTrue(utils.isWebElementDisplayed("Post", "xpath#subscribedReddit", 15));
//            String str_ExpURL = ExtractValueFromJSONFile("TD_RedditApp", "redditURL");
//            Assert.assertEquals(driver.getCurrentUrl(), str_ExpURL);
//            String createdPost = post.createPost();
//            Assert.assertTrue(utils.isWebElementDisplayed("Post", "classname#createdPost", 15));
//            Assert.assertEquals(utils.GetTextFromElement("Post", "classname#createdPost"), createdPost);
//            Assert.assertTrue(post.downVote()); // Pressing Down Vote arrow
//            Thread.sleep(1000); //Given for Viewing While clicking Arrows
//            String downVote = utils.getWebElement("Post", "xpath#downVote", 15).getAttribute("aria-pressed");
//            Assert.assertEquals(downVote, "true");
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println(e.getCause());
//            System.out.println(e.getMessage());
//        }
//    }
}
