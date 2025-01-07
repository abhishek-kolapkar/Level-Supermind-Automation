package base;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import pages.HomePage;
import pages.LandingPage;
import pages.LoginPage;
import pages.RegisterPage;
import utils.Helper;
import utils.Notification;

public class TestBase {
  public static AndroidDriver driver;
  protected Properties properties;

  protected Helper helper;
  protected LandingPage landingPage;
  protected LoginPage loginPage;
  protected Notification notification;
  protected RegisterPage registerPage;
  protected HomePage homePage;

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

          System.out.println("Session started...");
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
      }
    }
    
    @BeforeClass
    public void AppActions() {
      helper = new Helper(driver);
      landingPage = new LandingPage(driver);
      loginPage = new LoginPage(driver);
      notification = new Notification(driver);

      // new-user
      registerPage = new RegisterPage(driver);

      // home-screen
      homePage = new HomePage(driver);
    }

  @AfterTest
  public void teardown() {
    if (driver != null) {
      driver.quit();
      System.out.println("Session terminated...");
    }
  }
}
