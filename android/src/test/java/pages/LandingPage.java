package pages;

import org.openqa.selenium.WebElement;

import base.ScreenBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import utils.Helper;

public class LandingPage extends ScreenBase {
  /**
   * constructor
   */
  public LandingPage(AndroidDriver driver) {
    super(driver);
  }

  /**
   * locators
   */
  @AndroidFindBy(xpath = "//android.widget.TextView[@text='Sign in']")
  WebElement screenTitle;

  /**
   * element-interaction methods
   */

  /**
   * click on login button
   */
  public void clickLoginBtn() {
    WebElement loginBtn = Helper.waitForElement(
        20, "//android.widget.TextView[@text='Sign In']");

    loginBtn.click();
  }

  /**
   * get signin screen title
   */
  public String getScreenTitle() {
    return screenTitle.getText();
  }
}
