package testcases;

import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestCase1 {

	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	@BeforeTest
	public void setReport() {
		htmlReporter = new ExtentHtmlReporter("./reports/extent.html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Autoamtion Test Results");
		htmlReporter.config().setTheme(Theme.STANDARD);
		
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Automation Tester", "Raja Sekhar Reddy");
		extent.setSystemInfo("Organization", "Practise");
		extent.setSystemInfo("Build Number", "REL-8769");
	}
	@AfterTest
	public void afterReport() {
		extent.flush();
	}
	@Test
	public void doUserReg() {
		test=extent.createTest("Login Test");
		Assert.fail("User Reg Failed");
		System.out.println("Regrestation User");
	}
	@Test
	public void doLogin() {
		test=extent.createTest("Login Test");
		System.out.println("Executing Login Test");
	}

	@Test
	public void isSkip() {
		test=extent.createTest("Login Test");
		throw new SkipException("Skipping the test case");
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		
		if(result.getStatus()==ITestResult.FAILURE) {
			String methodName = result.getMethod().getMethodName();
			String logText="<b>"+"TEST CASE: -"+methodName.toUpperCase()+" FAILED"+"</b>";
			Markup m=MarkupHelper.createLabel(logText, ExtentColor.RED);
			test.fail(m);
		}else if(result.getStatus()==ITestResult.SKIP) {
			String methodName = result.getMethod().getMethodName();
			String logText="<b>"+"TEST CASE: -"+methodName.toUpperCase()+" SKIPPED"+"</b>";
			Markup m=MarkupHelper.createLabel(logText, ExtentColor.ORANGE);
			test.skip(m);
		}else if(result.getStatus()==ITestResult.SUCCESS) {
			String methodName = result.getMethod().getMethodName();
			String logText="<b>"+"TEST CASE: -"+methodName.toUpperCase()+" PASSED"+"</b>";
			Markup m=MarkupHelper.createLabel(logText, ExtentColor.GREEN);
			test.pass(m);
			
		}
		
	}
}
