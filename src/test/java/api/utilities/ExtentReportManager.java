package api.utilities;

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

public class ExtentReportManager implements ITestListener {
	
	// Responsible for UI/look and feel of the Report
	public ExtentSparkReporter sparkReporter;
	// Responsible for projection of common data like environment info, user info, operating systme, project report..etc
	public ExtentReports extent;
	// Responsible for creation of entries - any tests got failed 
	public ExtentTest test;
	
	String repName;
	
	// Executes only once before starting of tests
	public void onStart(ITestContext testContext) {

		// Entries for reporting with time stamp
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	    repName = "Test-Report-" + timeStamp + ".html";

	    // Below is the location in which reports are getting created
	    // .\\ represents current project location
	    sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);
	    
	    // Name of the report
	    sparkReporter.config().setDocumentTitle("RestAssuredAutomation Project");
	    
	    // Name of the API
	    sparkReporter.config().setReportName("Pet Store Users API");
	    sparkReporter.config().setTheme(Theme.DARK);

	    extent = new ExtentReports();
	    extent.attachReporter(sparkReporter);
	    extent.setSystemInfo("Application", "Pet Store Users API");
	    extent.setSystemInfo("Operating System", System.getProperty("os.name"));
	    extent.setSystemInfo("User Name", System.getProperty("user.name"));
	    extent.setSystemInfo("Environment", "QA");
	    extent.setSystemInfo("user", "Srikanth");
	    // Need to check if anything needs to be updated here
	}
	

	// Below three methods create entries based on test statuses like pass, fail, skip 
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, "Test Passed");
	}
	
	
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
	}
	
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}
	
	/*
	 * Below method is called at the end of the test execution.
       It calls the flush() method on the extent instance to save the generated report.
       Without executing below method, report cannot be generated
	 */
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

}
