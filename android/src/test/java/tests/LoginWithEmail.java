package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;
import utils.Helper;
import utils.Notification;

public class LoginWithEmail extends TestBase {
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
  public void openEmailScreen() {
    loginPage.openEmailTab();

    Assert.assertTrue(
        loginPage.isFieldVisible(),
        "Email field is not accessible");
    System.out.println("<----- Email field visible ----->");
  }

  /**
   * verify valid email & assert valid/invalid email
   */
  @Test(priority = 3, dependsOnMethods = { "openEmailScreen" })
  public void verifyValidEmail() {
    String userEmail = properties.getProperty("userEmail");

    loginPage.validateInput("Email", userEmail);
  }

  /**
   * retrieve OTP from email notification & enter it
   */
  @Test(priority = 4, dependsOnMethods = { "verifyValidEmail" })
  public void retrieveAndEnterOTP() {
    Assert.assertTrue(loginPage.isOtpScreenVisible());

    String OTP = Notification.getEmailOTP();

    System.out.println("<----- OTP retrived ----->");
    loginPage.enterInput(OTP);
    System.out.println("<----- OTP entered ----->");
  }

  /**
   * verify entered OTP
   */
  @Test(priority = 5, dependsOnMethods = { "retrieveAndEnterOTP" })
  public void verifyEmailByOTP() {
    loginPage.continueBtn.click();

    Helper.waitTill(5);
  }

  /**
   * verify user status(existing / new)
   */
  @Test(priority = 6, dependsOnMethods = { "verifyEmailByOTP" })
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
