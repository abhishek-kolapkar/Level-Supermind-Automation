package reports;

import java.util.HashMap;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import base.TestBase;
import utils.CaptureTest;

public class TestListner implements ITestListener {
  public HashMap<String, ExtentTest> allTests = new HashMap<>();

  String Report_File_Path = System
      .getProperty("user.dir") +
      "\\src\\test\\resources\\reports\\TestResult.html";

  // default constructor
  public TestListner() {
  }

  // Initialize the report at the start of the test suite
  @Override
  public void onStart(ITestContext iTestContext) {
    ReportManager.createInstance(Report_File_Path);
  }

  // Create a new test in the report for the current test method
  @Override
  public void onTestStart(ITestResult iTestResult) {
    ExtentTest extentTest = ReportManager
        .getInstance()
        .createTest(iTestResult.getMethod().getMethodName());

    allTests.put(iTestResult.getTestClass().getName(), extentTest);
    ReportManager.setTest(extentTest);
  }

  // Mark the test as passed in the report
  @Override
  public void onTestSuccess(ITestResult iTestResult) {
    String testName = CaptureTest.takeScreenshot(
        TestBase.driver,
        iTestResult.getMethod().getMethodName());

    ReportManager
        .getTest()
        .assignCategory(iTestResult.getTestClass().getName())
        .pass(MarkupHelper.createLabel("Test Passed", ExtentColor.GREEN))
        .info(
            "Screenshot",
            MediaEntityBuilder.createScreenCaptureFromPath(testName).build());
  }

  // Mark the test as failed in the report and add the exception details
  @Override
  public void onTestFailure(ITestResult iTestResult) {
    String testName = CaptureTest.takeScreenshot(
        TestBase.driver,
        iTestResult.getMethod().getMethodName());

    ReportManager
        .getTest()
        .fail(MarkupHelper.createLabel("Test Failed", ExtentColor.RED))
        .fail(iTestResult.getThrowable())
        .info(
            "Screenshot",
            MediaEntityBuilder.createScreenCaptureFromPath(testName).build());
  }

  // Mark the test as skipped in the report
  @Override
  public void onTestSkipped(ITestResult iTestResult) {
    String testName = CaptureTest.takeScreenshot(
        TestBase.driver,
        iTestResult.getMethod().getMethodName());

    ReportManager
        .getTest()
        .skip(MarkupHelper.createLabel("Test Skipped", ExtentColor.ORANGE))
        .skip(iTestResult.getThrowable())
        .info(
            "Screenshot",
            MediaEntityBuilder.createScreenCaptureFromPath(testName).build());
  }

  // Flush the report after all tests have been executed
  @Override
  public void onFinish(ITestContext iTestContext) {
    ReportManager.flushReports();
  }
}
