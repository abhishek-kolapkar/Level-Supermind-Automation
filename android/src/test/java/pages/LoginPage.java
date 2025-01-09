package pages;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import base.ScreenBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import utils.Helper;

public class LoginPage extends ScreenBase {
  /**
   * constructor
   */
  public LoginPage(AndroidDriver driver) {
    super(driver);
  }

  /**
   * locators
   */
  @AndroidFindBy(xpath = "//android.widget.TextView[@text='Email']")
  public WebElement emailTab;

  @AndroidFindBy(xpath = "//android.widget.TextView[@text='Phone']")
  public WebElement phoneTab;

  @AndroidFindBy(xpath = "//android.widget.TextView[@text='We have sent a code to ']")
  public WebElement otpScreen;

  /**
   * common-locators
   */
  @AndroidFindBy(xpath = "//android.widget.EditText")
  public WebElement inputField;

  @AndroidFindBy(xpath = "(//android.view.View[@content-desc='Arrow Right'])[1]")
  public WebElement continueBtn;

  @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Back Arrow']")
  public WebElement backBtn;

  @AndroidFindBy(xpath = "//android.widget.Toast[1]")
  WebElement toast;

  /**
   * element-interaction methods
   */

  /**
   * open email-tab
   */
  public void openEmailTab() {
    if (!emailTab.isSelected())
      emailTab.click();
    else
      System.out.println("Already opened");
  }

  /**
   * open phone-tab
   */
  public void openPhoneTab() {
    if (!phoneTab.isSelected())
      phoneTab.click();
    else
      System.out.println("Already opened");
  }

  /**
   * check if input field visible
   */
  public Boolean isFieldVisible() {
    return inputField.isDisplayed();
  }

  /**
   * enter data(email/phone) into input field
   */
  public void enterInput(String data) {
    inputField.clear();
    inputField.click();

    inputField.sendKeys(data);
  }

  /**
   * check if alert-toast appeared
   */
  public Boolean isToastVisible() {
    if (toast != null) {
      System.out.println(toast.getText());
      return true;
    }

    return false;
  }

  /**
   * check if otp-screen open
   */
  public Boolean isOtpScreenVisible() {
    return otpScreen.isDisplayed();
  }

  /**
   * cancel GMS prompt
   */
  public void cancleGMS() {
    WebElement closeBtn = Helper.waitForElement(10,
        "//android.widget.Button[@text, 'Deny']");

    if (closeBtn.isDisplayed()) {
      System.out.println("<----- GMS screen open ----->");
      closeBtn.click();
      System.out.println("<----- GMS screen close (Not allow to copy!) ----->");
    }
  }

  /**
   * Validation for email / phone number
   */
  public void validateInput(String type, String input) {
    String validEmailRegex = "^[a-z][a-z0-9.]*@(gmail|email|outlook|yahoo)\\.com$";
    String validPhoneNumberRegex = "^[6-9][0-9]{9}$";

    String regex = type.equalsIgnoreCase("email") ? validEmailRegex : validPhoneNumberRegex;

    enterInput(input);
    System.out.println("<----- %s entered ----->".formatted(type));
    continueBtn.click();

    if (input.trim().isEmpty() || !input.matches(regex)) {
      try {
        // check if invalid input toast message
        if (isToastVisible()) {
          System.out.println("<----- Invalid %s: %s ----->".formatted(type, input));
        }
      } catch (Exception e) {
        if (isOtpScreenVisible()) {
          System.out.println("<----- Invalid %s accepted: %s ----->".formatted(type, input));
          Assert.fail("Invalid %s accepted: %s".formatted(type, input));

          // navigate back to email screen
          Helper.navigateToBack();
        }
      }
    } else {
      // check if valid email accepted
      if (isOtpScreenVisible()) {
        System.out.println("Valid %s: %s".formatted(type, input));
      }
    }
  }
}
