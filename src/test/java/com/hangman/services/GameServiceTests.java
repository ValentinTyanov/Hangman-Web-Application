package com.hangman.services;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import com.hangman.model.Game;
import com.hangman.model.UnusedLetter;
import com.hangman.repository.GameRepository;
import com.hangman.repository.UnusedLetterRepositoryImpl;
import com.hangman.service.GameServiceImpl;
import com.hangman.service.WordService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GameServiceTests {

  @Mock GameRepository gameRepository;
  @Mock WordService wordService;
  @Mock UnusedLetterRepositoryImpl unusedLetterRepository;
  @InjectMocks public GameServiceImpl gameService;

  @Test
  public void shouldHaveAttemptsLeftEqualToWordLength() {
    Game game = new Game();
    String word = "Candle";
    game.setWord(word);
    game.setAttemptsLeft(game.getWord().length());
    assertThat(game.getAttemptsLeft()).isEqualTo(word.length());
  }

  @Test
  public void shouldNotBeEmptyWordInProgressAfterSetup() {
    Game game = new Game();
    game.setWord("Patriarchy");
    gameService.setupWordInProgress(game);
    assertThat(game.getWordInProgress()).isNotNull();
  }

  @Test
  public void shouldInitiallyContainUnderscoreWordInProgressAfterSetup() {
    Game game = new Game();
    game.setWord("Pestilence");
    gameService.setupWordInProgress(game);
    assertThat(game.getWordInProgress()).contains("_");
  }

  @Test
  public void shouldReduceAttemptsAfterTryLetter() {
    Game game = new Game();
    game.setWord("Quicksort");
    String word = game.getWord();
    game.setAttemptsLeft(word.length());
    gameService.setupWordInProgress(game);
    List<UnusedLetter> letterList = new ArrayList<UnusedLetter>();
    UnusedLetter unusedLetter = new UnusedLetter();
    unusedLetter.setLetter('t');
    unusedLetter.setGame(game);
    letterList.add(unusedLetter);
    game.setUnusedLetters(letterList);
    when(gameRepository.findById(anyString())).thenReturn(game);
    gameService.tryLetter("asd123", 't');
    assertThat(game.getAttemptsLeft()).isEqualTo(word.length() - 1);
  }

  @Test
  public void shouldRemoveLetterFromAlphabetAfterTryLetter() {
    Game game = new Game();
    game.setWord("Quicksort");
    String word = game.getWord();
    game.setAttemptsLeft(word.length());
    gameService.setupWordInProgress(game);
    List<UnusedLetter> letterList = new ArrayList<UnusedLetter>();
    UnusedLetter letterOne = new UnusedLetter();
    UnusedLetter letterTwo = new UnusedLetter();
    letterOne.setGame(game);
    letterOne.setLetter('v');
    letterTwo.setGame(game);
    letterTwo.setLetter('o');
    letterList.add(letterOne);
    letterList.add(letterTwo);
    game.setUnusedLetters(letterList);
    when(gameService.findById(anyString())).thenReturn(game);
    gameService.tryLetter("asd123", 'o');
    assertThat(game.getUnusedLetters().size()).isEqualTo(1);
  }
}
