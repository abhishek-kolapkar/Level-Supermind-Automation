package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;
import utils.Helper;
import utils.Notification;

public class LoginWithPhone extends TestBase {
  /**
   * navigation to login screen
   */
  @Test(priority = 1)
  public void navigateToLoginScreen() {
    landingPage.clickLoginBtn();

    Assert.assertEquals(
        landingPage.getScreenTitle(),
        "Sign in",
        "Title not matched");
    System.out.println("<----- Sign in page open ----->");
  }

  /**
   * click on email tab & open email screen
   */
  @Test(priority = 2, dependsOnMethods = { "navigateToLoginScreen" })
  public void openPhoneScreen() {
    loginPage.openPhoneTab();

    Assert.assertTrue(
        loginPage.isFieldVisible(),
        "Phone field is not accessible");
    System.out.println("<----- Phone number field visible ----->");
  }

  /**
   * verify valid email & assert valid/invalid email
   */
  @Test(priority = 3, dependsOnMethods = { "openPhoneScreen" })
  public void verifyValidPhoneNumber() {
    String userPhone = properties.getProperty("userPhone");

    loginPage.validateInput("phone", userPhone);
  }

  /**
   * retrieve OTP from SMS notification & enter it
   */
  @Test(priority = 4, dependsOnMethods = { "verifyValidPhoneNumber" })
  public void retrieveAndEnterOTP() {
    Assert.assertTrue(loginPage.isOtpScreenVisible());

    loginPage.cancleGMS();

    String OTP = Notification.getPhoneOTP();

    System.out.println("<----- OTP retrived ----->");
    loginPage.enterInput(OTP);
    System.out.println("<----- OTP entered ----->");
  }

  /**
   * verify entered OTP
   */
  @Test(priority = 5, dependsOnMethods = { "retrieveAndEnterOTP" })
  public void verifyPhoneByOTP() {
    loginPage.continueBtn.click();

    Helper.waitTill(2);
  }

  /**
   * verify user status(existing / new)
   */
  @Test(priority = 6, dependsOnMethods = { "verifyPhoneByOTP" })
  public void verifyUserStatus() {
    try {
      if (registerPage.isRegisterScreenOpen()) {
        System.out.println("<----- New user detected: Proceed ----->");
      }
    } catch (Exception e) {
      System.out.println("<----- User is already registered ----->");
    }
  }
}
