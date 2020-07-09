package com.hangman.integration;

import static org.assertj.core.api.Assertions.assertThat;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoseTest {
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
  public void shouldFail_To_Guess_Word_By_Pressing_Wrong_Letters_And_Return_Defeat_Page() {
    WebElement cheatWord = driver.findElement(By.cssSelector(".cheatButton"));
    cheatWord.click();
    driver.navigate().refresh();
    cheatWord = driver.findElement(By.cssSelector(".cheatButton"));
    String actualWord = cheatWord.getText().toLowerCase();
    Set<Character> alphabet =
        new HashSet<>(
            Arrays.asList(
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
                'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
    Set<Character> correctLetters = new HashSet<>();
    IntStream.range(0, actualWord.length())
        .forEach(index -> correctLetters.add(actualWord.charAt(index)));
    alphabet.removeAll(correctLetters);
    int count = 0;
    WebElement webElement = null;
    for (char letter : alphabet) {
      if (count == actualWord.length()) {
        break;
      }
      String selector = "input[value='" + letter + "'" + "]";
      webElement = driver.findElement(By.cssSelector(selector));
      webElement.click();
      count++;
    }
    webElement = driver.findElement(By.cssSelector("div.title > h1"));
    String header = webElement.getText();
    assertThat(header).isEqualTo("Game Over");
  }
}
