package com.hangman.integration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AttemptsReductionPageObject extends PageObject {

  @FindBy(xpath = "//p[contains(text(),'Attempts left:')]")
  private WebElement attemptsParagraph;

  @FindBy(xpath = "//body//input[3]")
  private WebElement randomButton;

  public AttemptsReductionPageObject(WebDriver driver) {
    super(driver);
  }

  public void clickRandomButton() {
    this.randomButton.click();
  }

  public Integer getAttempts() {
    String attemptsParagraphText = this.attemptsParagraph.getText();
    String[] tokens = attemptsParagraphText.split(": ");
    int attempts = Integer.parseInt(tokens[1]);
    return attempts;
  }
}
