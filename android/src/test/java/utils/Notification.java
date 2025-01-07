package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import base.ScreenBase;
import io.appium.java_client.android.AndroidDriver;

public class Notification extends ScreenBase {
  public Notification(AndroidDriver driver) {
    super(driver);
  }

  /**
   * clear all notifications
   */
  public void clearAllNotifications() {
    driver.openNotifications();

    try {
      Thread.sleep(2000); // wait for notifications to open

      WebElement clearBtn = driver.findElement(By.id("com.android.systemui:id/dismiss_view"));

      if (clearBtn.isDisplayed()) {
        clearBtn.click();
        System.out.println("Notifications cleared...");
      } else {
        System.out.println("Notifications not available...");
      }

    } catch (Exception e) {
      System.out.println("No such notifications available...");
    }

    helper.navigateToBack();
  }

  /**
   * retrieve OTP from notification
   */
  public String getOTP(String otpNotificationTitle, String otpNotificationText) {
    driver.openNotifications();

    try {
      WebElement notificationTitle = helper.waitForElement(30, otpNotificationTitle);

      Assert.assertTrue(notificationTitle.isDisplayed(), "Notification not received...");

      if (notificationTitle.isDisplayed()) {
        System.out.println("Notification received...");

        String notificationText = helper.waitForElement(5, otpNotificationText).getText();

        String OTP = helper.extractOTP(notificationText);

        if (OTP != null) {
          System.out.println("OTP retrived successfully...");
          return OTP;
        }
      }
    } catch (Exception e) {
      System.out.println("No relevant notification displayed...");
    } finally {
      helper.navigateToBack();
    }

    // if no OTP is found
    return null;
  }

  /**
   * use getOTP() for email
   */
  public String getEmailOTP() {
    String otpNotificationTitle = "//android.widget.TextView[@resource-id='android:id/title' and @text='LEVEL']";
    String otpNotificationText = "//android.widget.TextView[contains(@text, 'Verification E-mail for Level App')]";

    return getOTP(otpNotificationTitle, otpNotificationText);
  }

  /**
   * use getOTP() for phone
   */
  public String getPhoneOTP() {
    String otpNotificationTitle = "//android.widget.TextView[@resource-id='android:id/title' and contains(@text, 'LEVELG')]";
    String otpNotificationText = "//android.widget.TextView[contains(@text, 'Your OTP for login to Level Supermind is')]";

    return getOTP(otpNotificationTitle, otpNotificationText);
  }
}
