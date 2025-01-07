package pages;

import org.openqa.selenium.WebElement;

import base.ScreenBase;
import io.appium.java_client.android.AndroidDriver;

public class RegisterPage extends ScreenBase {
  public RegisterPage(AndroidDriver driver) {
    super(driver);
  }

  public Boolean isRegisterScreenVisible() {
    WebElement screenTitle = helper.waitForElement(
        10,
        "//android.widget.TextView[contains(@text, 'personalised recommendations')]");

    return screenTitle.isDisplayed();
  }
}
