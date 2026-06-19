package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.baseclass;

public class ExtentReportManager implements ITestListener 
{
	public ExtentSparkReporter sparkReporter; // UI of report
	public ExtentReports extent; // populate common info on report
    public ExtentTest test; //	creating test case entries in the report and update status of the test methods
	
    String repName;
	public void OnStart(ITestContext context)
	{
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-" + timestamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+ repName);
		
		//sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/myreport.html");
		sparkReporter.config().setDocumentTitle("Opencart Automation Report");
		sparkReporter.config().setReportName("Opencart Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Machine Name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Tester Name", System.getProperty("user.name"));
		String os = context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		String browser = context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser Name", browser);
	}
	
	public void OnTestStart(ITestResult result)
	{
		System.out.println("This is for on start test..");
	}
	
	public void OnTestSuccessful(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.PASS, "Test case PASSED is:"+result.getName());
	}
	
	public void OnTestFaluire(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, "Test case FAILED is:"+result.getName());
		test.log(Status.FAIL, "Test case FAILED is:"+result.getThrowable());
		
		try 
		{
		String imgpath = new baseclass().CaptureScreen(result.getName());
		test.addScreenCaptureFromPath(imgpath);
		
		
	     }
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
}
	public void OnTestSkipped(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.SKIP, "Test case SKIPPED is:"+result.getName());
	}
	
	public void OnFinish(ITestContext context)
	{
		extent.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\"+repName;
		File extentReport = new File(pathOfExtentReport);
		
		try
		{
			Desktop.getDesktop().browse(extentReport.toURI());
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
