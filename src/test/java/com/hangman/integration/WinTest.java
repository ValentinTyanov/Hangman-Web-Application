package com.hangman.integration;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WinTest extends DriverLifeCycleManagement {
  private GamePage gamePage;

  @BeforeEach
  public void startGame() {
    driver.get("http://localhost:8080/");
    gamePage = new GamePage(driver);
    gamePage.startGameButton().click();
  }

  @Test
  public void shouldGuessWordByPressingCorrectLettersAndRetrieveVictoryPage() {
    gamePage.cheatButton().click();
    driver.navigate().refresh();

    String actualWord = gamePage.cheatButton().getText().toLowerCase();
    Set<Character> correctLetters = fillWithCorrectLetters(actualWord);

    clickCorrectLetterButtons(correctLetters);
    assertThat(gamePage.confirmationHeader()).isEqualTo("You won!");
  }

  private Set<Character> fillWithCorrectLetters(String actualWord) {
    char fc = actualWord.charAt(0);
    char lc = actualWord.charAt(actualWord.length() - 1);

    Set<Character> correctLetters = new HashSet<>();
    for (int i = 1; i < actualWord.length() - 1; i++) {
      char currLetter = actualWord.charAt(i);
      if (currLetter != fc && currLetter != lc) {
        correctLetters.add(currLetter);
      }
    }
    return correctLetters;
  }

  private void clickCorrectLetterButtons(Set<Character> correctLetters) {
    WebElement correctLetterButton = null;
    for (char letter : correctLetters) {
      String selector = "input[value='" + letter + "'" + "]";
      correctLetterButton = driver.findElement(By.cssSelector(selector));
      correctLetterButton.click();
    }
  }
}
