package com.hangman.integration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GamePage extends PageObject {

  @FindBy(css = "input[type='submit']")
  private WebElement startGameButton;

  @FindBy(css = ".cheatButton")
  private WebElement cheatButton;

  @FindBy(css = "div.title > h1")
  private WebElement header;

  public GamePage(WebDriver driver) {
    super(driver);
  }

  public WebElement startGameButton() {
    return this.startGameButton;
  }

  public WebElement cheatButton() {
    return this.cheatButton;
  }

  public String confirmationHeader() {
    return this.header.getText();
  }
}
