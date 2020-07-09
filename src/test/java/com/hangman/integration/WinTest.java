package com.hangman.integration;

import static org.assertj.core.api.Assertions.assertThat;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WinTest {
  public static WebDriver driver;

  @Before
  public void init() throws IOException {
    URL url = Thread.currentThread().getContextClassLoader().getResource("geckodriver.exe");
    System.setProperty("webdriver.gecko.driver", new File(url.getFile()).getCanonicalPath());
    driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    driver.get("http://localhost:8080/");
    WebElement firstName = driver.findElement(By.cssSelector("input[type='submit']"));
    firstName.click();
  }

  @After
  public void after() {
    driver.quit();
  }

  @Test
  public void shouldGuess_Word_By_Pressing_Correct_Letters_And_RedirectTo_Victory_Page() {
    WebElement cheatWord = driver.findElement(By.cssSelector(".cheatButton"));
    cheatWord.click();
    driver.navigate().refresh();
    cheatWord = driver.findElement(By.cssSelector(".cheatButton"));
    String actualWord = cheatWord.getText().toLowerCase();
    char fc = actualWord.charAt(0);
    char lc = actualWord.charAt(actualWord.length() - 1);
    Set<Character> correctLetters = new HashSet<>();
    for (int i = 1; i < actualWord.length() - 1; i++) {
      char currLetter = actualWord.charAt(i);
      if (currLetter != fc && currLetter != lc) correctLetters.add(currLetter);
    }
    WebElement webElement = null;
    for (char letter : correctLetters) {
      String selector = "input[value='" + letter + "'" + "]";
      webElement = driver.findElement(By.cssSelector(selector));
      webElement.click();
    }
    webElement = driver.findElement(By.cssSelector("div.title > h1"));
    String header = webElement.getText();
    assertThat(header).isEqualTo("You won!");
  }
}
