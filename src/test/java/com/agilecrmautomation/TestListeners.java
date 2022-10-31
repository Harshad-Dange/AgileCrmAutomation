package com.agilecrmautomation;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestListeners extends BaseClass implements ITestListener, ISuiteListener {

	ExtentReports extentReport;
	ExtentSparkReporter sparkReport;
	String reportPath;

	// Onstart method of ISuiteListener Interface
	public void onStart(ISuite suite) {		
		LocalDateTime dateTime=LocalDateTime.now(); // return the current date and time (Long)
		//format the date and time in 11_10_2022_8_10
		String timeStamp=dateTime.format(DateTimeFormatter.ofPattern("dd_MM_yyyy_hh_mm"));
		String folderName="Report_"+ timeStamp;
		System.out.println(folderName);
		
		reportPath=System.getProperty("user.dir")+"//ExecutionResult//"+folderName;
		//create file class instance to take control of folder path
		File file=new File(reportPath);
		//check if the folder exist; if not present then create new
		if(!file.exists()) {
			file.mkdir(); //create new file or folder at the desired path
		}
		setExtentReportDetails();
		
	}

	// Onfinish method of ISuiteListener Interface
	public void onFinish(ISuite suite) {
		System.out.println("This is onFinish Method of Suite");
		extentReport.flush();
	}

	public void onStart(ITestContext context) {
		System.out.println("This is onStart method of Test");
	}

	public void onFinish(ITestContext context) {
		System.out.println("This is onFinish method of Test");
	}

	synchronized public void onTestStart(ITestResult result) {
//		String methodName=result.getMethod().getMethodName();
		String methodName = result.getName();
		logger = extentReport.createTest(methodName);

		System.out.println("This is onTestStart method");
	}

	synchronized public void onTestSuccess(ITestResult result) {
		// add success information into extent report using logger variable
		logger.pass("Successfully executed the test:" + result.getName());
		System.out.println("This is onTestSuccess method");
	}

	synchronized public void onTestFailure(ITestResult result) {
		System.out.println("This is onTestFailure method");
		String outputPath=takeScreenShot(result,reportPath);
		logger.fail("The test execution is failed due to : " + result.getThrowable());
		logger.addScreenCaptureFromPath(outputPath);
	}

	synchronized public void onTestSkipped(ITestResult result) {
		logger.skip("The test is skipped due to the :" + result.getSkipCausedBy());
		System.out.println("This is onTestSkipped method");
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		System.out.println("This is onTestFailedWithTimeout method");
		onTestFailure(result);
	}
	private void setExtentReportDetails() {
		//execution report folder path
		String reportPath= this.reportPath+"//AutomationReport.html";
		sparkReport=new ExtentSparkReporter(reportPath);
		sparkReport.config().setDocumentTitle("AgileCrmAutomationReport");
		sparkReport.config().setReportName("Automation Report");
		// to add information into the html report, we need to create ExtentReports class instance
		extentReport=new ExtentReports();
		extentReport.attachReporter(sparkReport);
		extentReport.setSystemInfo("username", "cyber success");
		extentReport.setSystemInfo("environment", "test");
		extentReport.setSystemInfo("browser", "chrome");
	}
	

}
