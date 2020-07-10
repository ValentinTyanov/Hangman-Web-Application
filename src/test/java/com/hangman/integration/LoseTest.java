package com.hangman.integration;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoseTest extends DriverLifeCycleManagement {
  private GamePage gamePage;

  @BeforeEach
  public void startGame() {
    driver.get("http://localhost:8080/");
    gamePage = new GamePage(driver);
    gamePage.startGameButton().click();
  }

  @Test
  public void shouldFailToGuessWordByPressingWrongLettersAndRetrieveDefeatPage() {
    gamePage.cheatButton().click();
    driver.navigate().refresh();

    String actualWord = gamePage.cheatButton().getText().toLowerCase();
    Set<Character> wrongLettersAlphabet = removeCorrectLettersFromAlphabet(actualWord);

    int totalAttempts = actualWord.length();
    clickWrongLetterButtons(wrongLettersAlphabet, totalAttempts);

    assertThat(gamePage.confirmationHeader()).isEqualTo("Game Over");
  }

  private Set<Character> removeCorrectLettersFromAlphabet(String actualWord) {
    Set<Character> alphabet =
        new HashSet<>(
            Arrays.asList(
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
                'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));

    Set<Character> correctLetters = new HashSet<>();
    IntStream.range(0, actualWord.length())
        .forEach(index -> correctLetters.add(actualWord.charAt(index)));

    alphabet.removeAll(correctLetters);
    return alphabet;
  }

  private void clickWrongLetterButtons(Set<Character> wrongLettersAlphabet, int totalAttempts) {
    int count = 0;
    WebElement wrongLetterButton = null;

    for (char letter : wrongLettersAlphabet) {
      if (count == totalAttempts) {
        break;
      }

      String selector = "input[value='" + letter + "'" + "]";
      wrongLetterButton = driver.findElement(By.cssSelector(selector));
      wrongLetterButton.click();
      count++;
    }
  }
}
