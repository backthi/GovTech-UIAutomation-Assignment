package tests;

import Utils.Utils;
import Utils.*;
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.MediaEntityBuilder;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.markuputils.ExtentColor;
//import com.aventstack.extentreports.markuputils.MarkupHelper;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.aventstack.extentreports.reporter.configuration.Theme;

import Utils.extentreports.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import static Utils.GlobalValues.*;

import static Utils.extentreports.ExtentTestManager.startTest;
import static org.openqa.selenium.OutputType.BASE64;

public class BaseClass extends Utils
{
//    public static WebDriver driver;
    public static JavascriptExecutor js;
//    public HTMLR htmlReporter;
    public ExtentReports extent;
    public ExtentTest parent;
    public ExtentTest report;
    public ThreadLocal parentTest = new ThreadLocal();
    public ThreadLocal childTest = new ThreadLocal();
    public static String ROOTPATH;
    File file;
    public static String ExtentReport_FilePath;
    public static String ExtentReport_ConfigFilePath;
    public static String ScreenShot_Path;
    Utils utils = new Utils();

    ITestResult result;
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeSuite
    public void reportSetup()
    {
        try
        {
            setRootPath();
            ExtentReport_FilePath = ROOTPATH + "\\test-output\\ExtentReport.html";
            ExtentReport_ConfigFilePath = ROOTPATH + "\\src\\test\\resources\\Config\\extent-config.xml";
            ScreenShot_Path = ROOTPATH + "\\src\\test\\resources\\FailedScreenshots\\" + BaseClass.getcurrentdateandtime()+ ".png";
            if(System.getProperty("os.name").toLowerCase().contains("mac") || System.getProperty("os.name").toLowerCase().contains("linux"))
            {
                ExtentReport_FilePath = ExtentReport_FilePath.replace("\\", "/");
                ScreenShot_Path = ScreenShot_Path.replace("\\", "/");
                ExtentReport_ConfigFilePath = ExtentReport_ConfigFilePath.replace("\\", "/");
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    @BeforeTest
    @Parameters("browser")
    public void setup(String browser)
    {
        try
        {
            startTest(getClass().getName(), "Executing Test Name: " + getClass().getName());
            Utils utils = new Utils();
            Properties props = utils.readConfigFile();
            if(browser.equalsIgnoreCase("chrome"))
            {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-notifications");
                options.setAcceptInsecureCerts(true);
                if (props.getProperty("Headless").toUpperCase().equals("YES"))
                {
                    options.addArguments("--headless");
                }
                driver.set(new ChromeDriver(options));
            }
            else if(browser.equalsIgnoreCase("firefox"))
            {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-dev-shm-usage");
                driver.set(new FirefoxDriver(options));
                Thread.sleep(10000);
            }
            if(browser.equalsIgnoreCase("firefox"))
            {
                getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            }
            getDriver().manage().deleteAllCookies();
            getDriver().manage().window().maximize();
            getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            js = (JavascriptExecutor) getDriver();
            Thread.sleep(2000);
        }
        catch (Exception e)
        {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public static WebDriver getDriver() {
        //Get driver from ThreadLocalMap
        return driver.get();
    }


    @AfterTest
    @Parameters("browser")
    public void teardown(String browser)
    {
        if(browser.equalsIgnoreCase("chrome"))
        {
            getDriver().close();
            getDriver().quit();
        }
        else {
//            getDriver().quit();
        }
    }

    @AfterSuite
    public void reportTearTown()
    {
        try
        {
            ExtentManager.extentReports.flush();
//            extent.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public void setRootPath() throws IOException
    {
        file = new File(".");
        ROOTPATH = file.getCanonicalFile().getPath();

        ROOTPATH = ROOTPATH.replace("\\", "/");
        ROOTPATH = new String(ROOTPATH.replaceAll("%20", " "));
    }

    public String capture(WebDriver driver) throws IOException {
        try
        {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(ScreenShot_Path);
            FileUtils.copyFile(scrFile, destFile);
            return ScreenShot_Path;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
            return e.getMessage();
        }

    }

    public static String getcurrentdateandtime()
    {
        String str = null;
        try{
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
            Date date = new Date();
            str= dateFormat.format(date);
            str = str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
            return str;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
    }

}



