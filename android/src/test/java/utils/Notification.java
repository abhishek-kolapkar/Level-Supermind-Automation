package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;

public class Notification {
  public static AndroidDriver driver;

  /**
   * clear all notifications
   */
  public static void clearAllNotifications() {
    driver.openNotifications();

    try {
      WebElement clearBtn = driver.findElement(By.id("com.android.systemui:id/dismiss_view"));

      if (clearBtn.isDisplayed()) {
        clearBtn.click();
        System.out.println("<----- Notification cleared ----->");
      }
    } catch (Exception e) {
      System.out.println("<----- No such notifications available ----->");
    }

    Helper.navigateToBack();
  }

  /**
   * retrieve OTP from notification
   */
  public static String getOTP(String otpNotificationTitle, String otpNotificationText) {
    Helper.waitTill(3);
    driver.openNotifications();

    try {
      WebElement notificationTitle = Helper.waitForElement(
          60, otpNotificationTitle);

      Assert.assertTrue(
          notificationTitle.isDisplayed(),
          "Notification not received...");

      if (notificationTitle.isDisplayed()) {
        System.out.println("<----- Notification received ----->");

        String notificationText = Helper.waitForElement(
            2, otpNotificationText).getText();

        String OTP = Helper.extractOTP(notificationText);

        if (OTP != null) {
          return OTP;
        }
      }
    } catch (Exception e) {
      System.out.println("<----- No relevant notification displayed ----->");
      Assert.fail("<----- OTP not received ----->");
    } finally {
      Helper.navigateToBack();
    }

    // if no OTP is found
    return null;
  }

  /**
   * use getOTP() for email
   */
  public static String getEmailOTP() {
    String emailOTPNotificationTitle = "//android.widget.TextView[@resource-id='android:id/title' and @text='LEVEL']";
    String notificationText = "//android.widget.TextView[contains(@text, 'Verification E-mail for Level App')]";

    return getOTP(emailOTPNotificationTitle, notificationText);
  }

  /**
   * use getOTP() for phone
   */
  public static String getPhoneOTP() {
    String smsOTPNotificationTitle = "//android.widget.TextView[@resource-id='android:id/title' and contains(@text, 'LEVELG')]";
    String notificationText = "//android.widget.TextView[contains(@text, 'Your OTP for login to Level Supermind is')]";

    return getOTP(smsOTPNotificationTitle, notificationText);
  }
}
