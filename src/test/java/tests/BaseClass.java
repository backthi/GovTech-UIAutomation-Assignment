package tests;

import Utils.Utils;
import Utils.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.aventstack.extentreports.Status;

import static Utils.GlobalValues.*;
import static com.aventstack.extentreports.Status.*;
import static org.openqa.selenium.OutputType.BASE64;

public class BaseClass extends Utils
{
    public static WebDriver driver;
    public static JavascriptExecutor js;
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public static ExtentTest Suite;
    public ExtentTest parent;
    public ExtentTest report;
    public ThreadLocal parentTest = new ThreadLocal();
    public ThreadLocal childTest = new ThreadLocal();
    public static String ROOTPATH;
    File file;
    public static String ExtentReport_FilePath;
    public static String ScreenShot_Path;
    Utils utils = new Utils();

    ITestResult result;

    @BeforeSuite
    public void reportSetup()
    {
        try
        {
            setRootPath();
            ExtentReport_FilePath = ROOTPATH + "\\test-output\\ExtentReport.html";
            ScreenShot_Path = ROOTPATH + "\\src\\test\\resources\\FailedScreenshots\\" + BaseClass.getcurrentdateandtime()+ ".png";
            if(System.getProperty("os.name").toLowerCase().contains("mac") || System.getProperty("os.name").toLowerCase().contains("linux"))
            {
                ExtentReport_FilePath = ExtentReport_FilePath.replace("\\", "/");
                ScreenShot_Path = ScreenShot_Path.replace("\\", "/");
            }
            htmlReporter = new ExtentHtmlReporter(ExtentReport_FilePath);
            htmlReporter.config().setTheme(Theme.DARK);
            htmlReporter.config().setDocumentTitle("ExtentReport");
            htmlReporter.config().setEncoding("utf-8");
            htmlReporter.config().setReportName("Mavericks Web Automation Test Report");
            //htmlReporter.loadXMLConfig(new File(System.getProperty("C:\\Users\\LocalAdmin\\IdeaProjects\\Android_Mobile_Testing\\extentreport-config.xml")));
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
        }
        catch(Exception e)
        {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    @BeforeClass
    public void setup()
    {
        try
        {
            parent = extent.createTest(getClass().getName());
            parentTest.set(parent);
            Utils utils = new Utils();
            Properties props = utils.readConfigFile();
            if (props.getProperty("Browser").toUpperCase().equals("CHROME"))
            {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-notifications");
                if (props.getProperty("Headless").toUpperCase().equals("YES"))
                {
                    options.addArguments("--headless");
                }
                if(System.getProperty("os.name").toLowerCase().contains("mac") || System.getProperty("os.name").toLowerCase().contains("linux"))
                {
                    System.setProperty("webdriver.chrome.driver", ROOTPATH + "/src/test/resources/DriverExe/Chrome/chromedriver");
                }
                else if(System.getProperty("os.name").toLowerCase().contains("windows"))
                {
                    System.setProperty("webdriver.chrome.driver", ROOTPATH + "\\src\\test\\resources\\DriverExe\\Chrome\\chromedriver.exe");
                }
                driver = new ChromeDriver(options);
            }
            else if (props.getProperty("Browser").toUpperCase().equals("FIREFOX"))
            {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--disable-notifications");
                if(System.getProperty("os.name").toLowerCase().contains("mac") || System.getProperty("os.name").toLowerCase().contains("linux"))
                {
                    System.setProperty("webdriver.gecko.driver", ROOTPATH + "/src/test/resources/DriverExe/FireFox/geckodriver");
                }
                else if(System.getProperty("os.name").toLowerCase().contains("windows"))
                {
                    System.setProperty("webdriver.gecko.driver", ROOTPATH + "\\src\\test\\resources\\DriverExe\\FireFox\\geckodriver.exe");
                }
                driver = new FirefoxDriver(options);
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            js = (JavascriptExecutor) driver;
            Thread.sleep(2000);
        }
        catch (Exception e)
        {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public synchronized void setup(Method method) {
        report = parent.createNode(method.getName());
        childTest.set(report);
    }

    @AfterMethod
    public synchronized void getResult(ITestResult result) throws IOException
    {
        try
        {
            if (result.getStatus() == ITestResult.FAILURE)
            {
                report.log(FAIL, MarkupHelper.createLabel(result.getName() + " is FAILED due to below issues:", ExtentColor.RED ));
                report.log(FAIL, "Issue is " + result.getThrowable());
                //To capture screenshot path and store the path of the screenshot in the string "screenshotPath"
                //We do pass the path captured by this mehtod in to the extent reports using "logger.addScreenCapture" method.
                String screenshotPath = utils.getScreenShot(result.getName());
                //To add it in the extent report
                report.log(FAIL, "Screenshot: " + (Markup) report.addScreenCaptureFromPath(screenshotPath));
//                report.log(FAIL,"Screenshot: " + MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath.build()));
            }
            else if (result.getStatus() == ITestResult.SUCCESS)
            {
                report.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " is PASSED", ExtentColor.GREEN));
            }
            else if (result.getStatus() == ITestResult.SKIP)
            {
                report.createNode(result.getInstanceName());
                report.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " : " + result.getTestName() + " is SKIPPED", ExtentColor.ORANGE));
                report.log(SKIP, result.getThrowable());
            }
            extent.flush();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            e.getMessage();
        }
    }

        // ending test
        //endTest(logger) : It ends the current test and prepares to create HTML report
//        extent.endTest(report);


//        if (result.getStatus() == ITestResult.FAILURE) {
//
//            String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(BASE64);
//
//            report.log(FAIL, MarkupHelper.createLabel(result.getName() + " is FAILED due to below issues:",
//                    ExtentColor.RED ));
//            report.fail(result.getThrowable().getMessage());
//            report.log(FAIL,"Screenshot", MediaEntityBuilder.createScreenCaptureFromPath((base64Screenshot).build()));
//
//        } else if (result.getStatus() == ITestResult.SUCCESS) {
//
//            report.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " is PASSED", ExtentColor.GREEN));
//        } else {
//            report.createNode(result.getInstanceName());
//            report.log(Status.SKIP,
//                    MarkupHelper.createLabel(result.getMethod() + " : " + result.getTestName() + " is SKIPPED", ExtentColor.ORANGE));
//            report.skip(result.getThrowable());
//        }
//        extent.flush();
        //FileUtils.copyFile(new File(ExtentReport_FilePath), new File("C:\\Users\\LocalAdmin\\IdeaProjects\\ExtentReports_History\\ExtentReport_" + getcurrentdateandtime() + ".html"));
//    }

    @AfterClass
    public void teardown()
    {
        driver.close();
        driver.quit();
    }

    @AfterSuite
    public void reportTearTown()
    {
        try
        {
            extent.flush();
            //FileUtils.copyFile(new File(ExtentReport_FilePath), new File(ExtentReportHistory_Path));
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



