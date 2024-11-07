package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;

    public void onStart(ITestContext context){
        String timeStamp = new SimpleDateFormat("yyyy-mm-dd.HH.mm.ss").format(new Date());
        repName="TestReport - "+timeStamp+".html";

        sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);
        sparkReporter.config().setDocumentTitle("RestAssured Automation Project");
        sparkReporter.config().setReportName("User Creation - PetStore");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application","Pet Store User API");
        extent.setSystemInfo("OS",System.getProperty("os.name"));
        extent.setSystemInfo("User name",System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("user","Ferozkhan");

    }

    public void onTestSuccess(ITestResult result)
    {
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.PASS,"Test Passed...");
    }

    public void onTestFailure(ITestResult result)
    {
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.FAIL,"Test Failed...");
        test.log(Status.FAIL,result.getThrowable().getMessage());

    }

    public void onTestSkipped(ITestResult result)
    {
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.SKIP,"Test Skipped...");
        test.log(Status.SKIP,result.getThrowable().getMessage());

    }

    public void  onFinish(ITestContext context)
    {
        extent.flush();
    }


}
