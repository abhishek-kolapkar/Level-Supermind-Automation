package base;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ScreenBase {
  protected AndroidDriver driver;

  // constructor
  public ScreenBase(AndroidDriver driver) {
    this.driver = driver;

    PageFactory.initElements(new AppiumFieldDecorator(driver), this);
  }
}
