package Utils.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();
    public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("./ExtentReports/extent-report.html");
        reporter.config().setReportName("GovTechWGPPortalTests Extent Report");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Blog Name", "UI Automation");
        extentReports.setSystemInfo("Author", "Backthi");
        return extentReports;
    }
}
