package pages;

import org.openqa.selenium.WebElement;

import base.ScreenBase;
import io.appium.java_client.android.AndroidDriver;

public class HomePage extends ScreenBase {
  public HomePage(AndroidDriver driver) {
    super(driver);
  }

  public Boolean isHomeScreenVisible() {
    WebElement screenTitle = helper.waitForElement(
        10,
        "//android.widget.TextView[@text='Introducing Courses']");

    return screenTitle.isDisplayed();
  }
}
