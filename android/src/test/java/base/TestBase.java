package base;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import pages.LandingPage;
import pages.LoginPage;
import pages.RegisterPage;
import utils.CaptureTest;
import utils.Helper;
import utils.Notification;

public class TestBase {
  public static AndroidDriver driver;
  protected Properties properties;

  protected LandingPage landingPage;
  protected LoginPage loginPage;
  protected RegisterPage registerPage;

  @BeforeTest
  public void driverInit() {
    properties = new Properties();

    if (driver == null) {
      try {
        FileInputStream fileInputStream = new FileInputStream(
            System.getProperty("user.dir") + "/src/test/resources/properties/Properties.properties");
        properties.load(fileInputStream);

        // Set up UiAutomator2 options based on the properties file
        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName(properties.getProperty("platformName"));
        options.setDeviceName(properties.getProperty("deviceName"));
        options.setPlatformVersion(properties.getProperty("platformVersion"));
        options.setAppPackage(properties.getProperty("appPackage"));
        options.setAppActivity(properties.getProperty("appActivity"));

        options.setCapability("unicodeKeyboard", true);
        options.setCapability("resetKeyboard", true);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

        // Assign the driver to Notification & Helper class
        Notification.driver = driver;
        Helper.driver = driver;
        CaptureTest.driver = driver;

        System.out.println("<----- App started ----->");
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }

  @BeforeClass
  public void AppActions() {
    landingPage = new LandingPage(driver);
    loginPage = new LoginPage(driver);

    // new-user
    registerPage = new RegisterPage(driver);

    // clear-all notifications
    Notification.clearAllNotifications();
  }

  @AfterTest
  public void teardown() {
    if (driver != null) {
      driver.quit();
      System.out.println("<----- App closed ----->");
    }
  }
}
