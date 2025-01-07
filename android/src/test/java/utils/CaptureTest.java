package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import io.appium.java_client.android.AndroidDriver;

public class CaptureTest {

  public static String takeScreenshot(AndroidDriver driver, String testName) {
    try {
      // Create screenshots directory if it doesn't exist
      String ssDirPath = System.getProperty("user.dir") + "\\src\\test\\resources\\screenshots";
      new File(ssDirPath).mkdirs();

      // Generate a timestamp to ensure unique file names
      String timestamp = new SimpleDateFormat("yyMMddhhss").format(new Date());
      String screenshotFile = ssDirPath + "\\" + testName + "_" + timestamp + ".png";

      // Take the screenshot
      File screenshot = ((AndroidDriver) driver).getScreenshotAs(OutputType.FILE);

      // save screenshot to specified path
      FileUtils.copyFile(screenshot, new File(screenshotFile));

      return screenshotFile;

    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
