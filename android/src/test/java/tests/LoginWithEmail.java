package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;

public class LoginWithEmail extends TestBase {
  @Test(priority = 1)
  public void navigateToLoginPage() {
    notification.clearAllNotifications();
    landingPage.clickLoginBtn();

    String actualTitle = landingPage.getScreenTitle();
    Assert.assertEquals(actualTitle, "Sign in", "Title not matched");
  }

  @Test(priority = 2, dependsOnMethods = { "navigateToLoginPage" })
  public void openEmailScreen() {
    loginPage.openEmailTab();

    Boolean isVisible = loginPage.isFieldVisible();
    Assert.assertTrue(isVisible, "Email field is not accessible");
  }

  @Test(priority = 3, dependsOnMethods = { "openEmailScreen" })
  public void enterValidEmail() {
    String email = properties.getProperty("userEmail");

    loginPage.enterInputField(email);
  }

  @Test(priority = 4, dependsOnMethods = { "enterValidEmail" })
  public void verifyOTPScreenVisible() {
    String email = properties.getProperty("userEmail");

    loginPage.continueBtn.click();

    loginPage.validateEmailFormat(email);

    Boolean isVisible = loginPage.isOtpScreenVisible();
    Assert.assertTrue(isVisible, "Please enter a valid email id");
  }

  @Test(priority = 5, dependsOnMethods = { "verifyOTPScreenVisible" })
  public void retrieveAndEnterOTP() {
    String OTP = notification.getEmailOTP();

    Boolean isVisible = loginPage.isFieldVisible();
    Assert.assertTrue(isVisible, "Email field is not accessible");

    loginPage.enterInputField(OTP);
  }

  @Test(priority = 6, dependsOnMethods = { "retrieveAndEnterOTP" })
  public void verifyEmailByOTP() {
    loginPage.continueBtn.click();
  }

  @Test(priority = 7, dependsOnMethods = { "verifyEmailByOTP" })
  public void verifyUserStatus() {
    try {
      helper.waitTill(2000);

      if (homePage.isHomeScreenVisible()) {
        System.out.println("Existing user detected: Welcome back...");
      } else if (registerPage.isRegisterScreenVisible()) {
        System.out.println("New user detected: Welcome to Level Supermind");
      } else {
        System.out.println("No user detected");
      }
    } catch (Exception e) {
      System.out.println("Unable to locate you status");
    }
  }
}
