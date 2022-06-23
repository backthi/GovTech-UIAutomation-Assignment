package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.By;
import tests.BaseClass;

import java.io.File;
import java.io.IOException;

import static tests.BaseClass.ROOTPATH;

public class GlobalValues
{
    public static String ExtentReport_FilePath;
    public static String ScreenShot_Path;

    public static By next_Btn = By.xpath("//button[@id='next-btn']");
    public static By save_Btn = By.xpath("//button[@id='save-btn']");
    public static By draftSaved_Text = By.xpath("//div[contains(text(),'Draft Saved')]");
}
