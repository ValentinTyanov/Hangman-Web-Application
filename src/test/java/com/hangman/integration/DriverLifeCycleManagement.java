package com.hangman.integration;

import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverLifeCycleManagement {
  protected static WebDriver driver;

  @BeforeAll
  public static void setUp() throws IOException {
    URL url = Thread.currentThread().getContextClassLoader().getResource("geckodriver.exe");
    System.setProperty(
        "webdriver.gecko.driver",
        new File(url.getFile()).getCanonicalPath().replaceAll("%20", " "));
    driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @AfterAll
  public static void cleanUp() {
    driver.manage().deleteAllCookies();
    driver.quit();
  }
}
