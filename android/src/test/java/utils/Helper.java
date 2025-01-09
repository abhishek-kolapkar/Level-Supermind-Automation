package utils;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class Helper {
  public static AndroidDriver driver;

  /**
   * explicit wait
   */
  public static WebElement waitForElement(int duration, String locator) {
    return new WebDriverWait(driver, Duration.ofSeconds(duration))
        .until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath(locator)));
  }

  /**
   * wait
   */
  public static void waitTill(int ofSeconds) {
    try {
      Thread.sleep(ofSeconds * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * navigate to back
   */
  public static void navigateToBack() {
    driver.navigate().back();
  }

  /**
   * extract a 6-digit OTP from the given text
   */
  protected static String extractOTP(String text) {
    String OTP = text.replaceAll("[^0-9]", "").substring(0, 6);

    return OTP;
  }
}
