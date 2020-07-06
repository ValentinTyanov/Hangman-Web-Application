package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestClass {
  public static void main(String[] args) {

    System.setProperty("webdriver.gecko.driver", "C:\\Selenium 3.14\\geckodriver.exe");

    WebDriver driver = new FirefoxDriver();
  }
}
