package base;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.Helper;

public class ScreenBase {
  protected AndroidDriver driver;
  protected Helper helper;

  // constructor
  public ScreenBase(AndroidDriver driver) {
    this.driver = driver;

    PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    helper = new Helper(driver);
  }
}
