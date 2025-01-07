package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportManager {
  public static ExtentReports extentReports;
  public static ExtentTest test;
  public static ExtentTest child;

  // Initialize and configure the ExtentReports instance
  public static ExtentReports createInstance(String reportPath) {
    if (extentReports == null) {
      ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);

      reporter.config().setDocumentTitle("Level Supermind Automation | Android");
      reporter.config().setReportName("Tests Report");
      reporter.config().setTheme(Theme.DARK);
      reporter.config().setEncoding("UTF-8");
      reporter.config().setTimelineEnabled(true);

      extentReports = new ExtentReports();
      extentReports.attachReporter(reporter);
    }

    return extentReports;
  }

  // Get the current ExtentReports instance
  public static ExtentReports getInstance() {
    if (extentReports == null) {
      throw new IllegalStateException("ExtentReports is not initialized. Call createInstance() first.");
    }
    return extentReports;
  }

  // Set and get the main test
  public static void setTest(ExtentTest testInstance) {
    test = testInstance;
  }

  public static ExtentTest getTest() {
    if (test == null) {
      throw new IllegalStateException("Test is not initialized. Ensure setTest() is called.");
    }
    return test;
  }

  // Set and get the child test
  public static void setchild(ExtentTest childInstance) {
    child = childInstance;
  }

  public static ExtentTest getchild() {
    if (child == null) {
      throw new IllegalStateException("Child test is not initialized. Ensure setchild() is called.");
    }
    return child;
  }

  // Flush the ExtentReports instance
  public static void flushReports() {
    if (extentReports != null) {
      extentReports.flush();
    }
  }
}
