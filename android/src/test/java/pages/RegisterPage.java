package pages;

import org.openqa.selenium.WebElement;

import base.ScreenBase;
import io.appium.java_client.android.AndroidDriver;
import utils.Helper;

public class RegisterPage extends ScreenBase {
  /**
   * constructor
   */
  public RegisterPage(AndroidDriver driver) {
    super(driver);
  }

  /**
   * check if home-screen open
   */
  public Boolean isRegisterScreenOpen() {
    WebElement registerScreen = Helper.waitForElement(
        10,
        "//android.widget.TextView[contains(@text, 'personalised recommendations')]");

    try {
      System.out.println("Checking if register screen is displayed...");
      return registerScreen.isDisplayed();

    } catch (Exception e) {
      System.out.println("Register screen not found: " + e.getMessage());
      return false;
    }
  }
}
