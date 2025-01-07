package pages;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import base.ScreenBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends ScreenBase {
  public LoginPage(AndroidDriver driver) {
    super(driver);
  }

  // locators
  @AndroidFindBy(xpath = "//android.widget.TextView[@text='Email']")
  public WebElement emailTab;

  @AndroidFindBy(xpath = "//android.widget.TextView[@text='Phone']")
  public WebElement phoneTab;

  @AndroidFindBy(xpath = "//android.widget.TextView[@text='We have sent a code to ']")
  public WebElement otpScreen;

  // common-locators
  @AndroidFindBy(xpath = "//android.widget.EditText")
  public WebElement inputField;

  @AndroidFindBy(xpath = "(//android.view.View[@content-desc='Arrow Right'])[1]")
  public WebElement continueBtn;

  @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Back Arrow']")
  public WebElement backBtn;

  // element-interaction methods
  public void openEmailTab() {
    if (!emailTab.isSelected())
      emailTab.click();
    else
      System.out.println("Already opened");
  }

  public void openPhoneTab() {
    if (!phoneTab.isSelected())
      phoneTab.click();
    else
      System.out.println("Already opened");
  }

  public Boolean isFieldVisible() {
    return inputField.isDisplayed();
  }

  public void enterInputField(String data) {
    inputField.clear();
    inputField.click();

    inputField.sendKeys(data);
  }

  public void validateEmailFormat(String email) {
    String validEmailRegex = "^[a-z][a-z0-9.]*@(gmail|email|outlook|yahoo)\\.com$";

    String alertErrorText = helper.waitForElement(
        20, "//android.widget.Toast[1]").getText();

    // check if empty or only whitespace contains
    if (email.trim().isEmpty()) {
      Assert.assertEquals(alertErrorText, "Please enter a valid email id");
    } else if (!email.matches(validEmailRegex)) {
      Assert.assertEquals(alertErrorText, "Please enter a valid email id");
    }
  }

  public void validatePhoneFormat(String phone) {
    // Define a regex for valid phone numbers
    // 10 digits starting with a valid digit like 6-9 for India
    String validPhoneNumberRegex = "^[6-9][0-9]{9}$";

    String alertErrorText = helper.waitForElement(
        20, "//android.widget.Toast[1]").getText();

    // check if empty or only whitespace contains
    if (phone.trim().isEmpty()) {
      Assert.assertEquals(alertErrorText, "Please enter a valid phone number");
    } else if (!phone.matches(validPhoneNumberRegex)) {
      Assert.assertEquals(alertErrorText, "Please enter a valid phone number");
    }
  }

  public Boolean isOtpScreenVisible() {
    return otpScreen.isDisplayed();
  }

  public void cancleGMS() {
    WebElement closeBtn = helper.waitForElement(
        15,
        "//android.widget.Button[@text, 'Deny']");

    if (closeBtn.isDisplayed()) {
      System.out.println("<------ GMS screen open ----->");
      closeBtn.click();
      System.out.println("<------ GMS screen close (Not allow to copy!) ----->");
    }
  }
}
