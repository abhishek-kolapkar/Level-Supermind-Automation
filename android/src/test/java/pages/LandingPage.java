package pages;

import org.openqa.selenium.WebElement;

import base.ScreenBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LandingPage extends ScreenBase {
  public LandingPage(AndroidDriver driver) {
    super(driver);
  }

  // locators
  @AndroidFindBy(xpath = "//android.widget.TextView[@text='Sign in']")
  WebElement screenTitle;

  // element-intraction methods
  // element-interaction methods
  public void clickLoginBtn() {
    // helper.waitTillVisible(20, loginBtn);
    WebElement loginBtn = helper.waitForElement(
        20, "//android.widget.TextView[@text='Sign In']");

    loginBtn.click();
  }

  public String getScreenTitle() {
    return screenTitle.getText();
  }
}
