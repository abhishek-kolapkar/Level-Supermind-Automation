package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;

public class LoginWithPhone extends TestBase {
  @Test(priority = 1)
  public void navigateToLoginPage() {
    notification.clearAllNotifications();
    landingPage.clickLoginBtn();

    String actualTitle = landingPage.getScreenTitle();
    Assert.assertEquals(actualTitle, "Sign in", "Title not matched");
  }

  @Test(priority = 2, dependsOnMethods = { "navigateToLoginPage" })
  public void openPhoneScreen() {
    loginPage.openPhoneTab();

    Boolean isVisible = loginPage.isFieldVisible();
    Assert.assertTrue(isVisible, "Phone field is not accessible");
  }

  @Test(priority = 3, dependsOnMethods = { "openPhoneScreen" })
  public void enterValidPhone() {
    String phone = properties.getProperty("userPhone");
    loginPage.enterInputField(phone);
  }

  @Test(priority = 4, dependsOnMethods = { "enterValidPhone" })
  public void verifyOTPScreenVisible() {
    String phone = properties.getProperty("userPhone");

    loginPage.continueBtn.click();

    loginPage.validatePhoneFormat(phone);

    loginPage.cancleGMS();

    Boolean isVisible = loginPage.isOtpScreenVisible();
    Assert.assertTrue(isVisible, "Please enter a valid phone number");
  }

  @Test(priority = 5, dependsOnMethods = { "verifyOTPScreenVisible" })
  public void retrieveAndEnterOTP() {
    String OTP = notification.getPhoneOTP();

    Boolean isVisible = loginPage.isFieldVisible();
    Assert.assertTrue(isVisible, "OTP field is not accessible");

    loginPage.enterInputField(OTP);
  }

  @Test(priority = 6, dependsOnMethods = { "retrieveAndEnterOTP" })
  public void verifyPhoneByOTP() {
    loginPage.continueBtn.click();
  }

  @Test(priority = 7, dependsOnMethods = { "verifyPhoneByOTP" })
  public void verifyUserStatus() {
    helper.waitTill(2000);

    Boolean isExistUser = homePage.isHomeScreenVisible();
    Boolean isNewUser = registerPage.isRegisterScreenVisible();

    try {
      if (isExistUser)
        Assert.assertTrue(isExistUser, "Welcome to Level Supermind");
      else if (isNewUser)
        Assert.assertTrue(isNewUser, "Welcome back...");
    } catch (Exception e) {
      System.out.println("Unable to locate you status");
    }
  }
}
