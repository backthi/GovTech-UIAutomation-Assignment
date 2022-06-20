package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import tests.BaseClass;

import java.io.File;
import java.io.IOException;

import static tests.BaseClass.ROOTPATH;

public class GlobalValues
{
    public static String ExtentReport_FilePath;
    public static String ScreenShot_Path;

    public GlobalValues() throws IOException
    {

    }
}
