package com.hangman.integration;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AttemptReductionTest extends DriverLifeCycleManagement {
  private GamePage gamePage;

  @BeforeEach
  public void startGame() {
    driver.get("http://localhost:8080/");
    gamePage = new GamePage(driver);
    gamePage.startGameButton().click();
  }

  @Test
  public void shouldReduceAttemptsAfterButtonClick() {
    AttemptsReductionPageObject gamePage = new AttemptsReductionPageObject(driver);

    int initialAttempts = gamePage.getAttempts();
    gamePage.clickRandomButton();
    int attemptsAfterClick = gamePage.getAttempts();

    assertThat(initialAttempts - 1).isEqualTo(attemptsAfterClick);
  }
}
