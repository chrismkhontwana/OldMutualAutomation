package Utillities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager
{
    public static final ExtentReports extentReports = new ExtentReports();
    public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("./TestReports/Old Mutual Results.html");
        reporter.config().setReportName("Old Mutual Regression Results");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Author", "Chris Mkhontwana");
        return extentReports;
    }
}
