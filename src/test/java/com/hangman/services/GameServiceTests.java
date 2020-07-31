package com.hangman.services;

import static org.assertj.core.api.Assertions.*;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import com.hangman.entities.Game;
import com.hangman.repositories.GameRepository;
import java.util.Arrays;
import java.util.HashSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GameServiceTests {

  @Mock GameRepository gameRepository;
  @Mock WordService wordService;
  @InjectMocks public GameServiceImpl gameService;

  @Test
  public void shouldSuccessfullyCreateGameAndReturnGameId() {
    when(wordService.generateWord()).thenReturn("Arson");
    String gameId = gameService.createGame();
    assertThat(gameId).isNotNull();
  }

  @Test
  public void shouldHaveAttemptsLeftEqualToWordLength() {
    Game game = new Game();
    String word = "Candle";
    game.setWord(word);
    game.setAttemptsLeft(game.getWord().length());
    assertThat(game.getAttemptsLeft()).isEqualTo(word.length());
  }

  @Test
  public void shouldNotBeEmptyOriginalLetterListAfterFill() {
    Game game = new Game();
    game.setWord("Bacteria");
    gameService.fillLetterLists(game);
    assertThat(game.getOriginalLettersList()).isNotEmpty();
  }

  @Test
  public void shouldNotContainUnderscoreInOriginalLetterListAfterFill() {
    Game game = new Game();
    game.setWord("Hospital");
    gameService.fillLetterLists(game);
    assertThat(game.getOriginalLettersList()).doesNotContain('_');
  }

  @Test
  public void shouldNotBeEmptyHiddenLetterListAfterFill() {
    Game game = new Game();
    game.setWord("Patriarchy");
    gameService.fillLetterLists(game);
    assertThat(game.getHiddenLettersList()).isNotEmpty();
  }

  @Test
  public void shouldInitiallyContainUnderscoreInHiddenLetterListAfterFill() {
    Game game = new Game();
    game.setWord("Pestilence");
    gameService.fillLetterLists(game);
    assertThat(game.getHiddenLettersList()).contains('_');
  }

  @Test
  public void shouldReduceAttemptsAfterCorrectTryLetter() {
    Game game = new Game();
    game.setWord("Quicksort");
    String word = game.getWord();
    game.setAttemptsLeft(word.length());
    gameService.fillLetterLists(game);
    game.setUnusedLetters(new HashSet<Character>('t'));
    when(gameService.getGame(anyString())).thenReturn(game);
    gameService.tryLetter("asd123", 't');
    assertThat(game.getAttemptsLeft()).isEqualTo(word.length() - 1);
  }

  @Test
  public void shouldReduceAttempts_v2_AfterWrongTryLetter() {
    Game game = new Game();
    game.setWord("Quicksort");
    String word = game.getWord();
    game.setAttemptsLeft(word.length());
    gameService.fillLetterLists(game);
    game.setUnusedLetters(new HashSet<Character>('t'));
    when(gameService.getGame(anyString())).thenReturn(game);
    gameService.tryLetter("asd123", 'v');
    assertThat(game.getAttemptsLeft()).isEqualTo(word.length() - 1);
  }

  @Test
  public void shouldRemoveLetterFromAlphabetAfterTryLetter() {
    Game game = new Game();
    game.setWord("Quicksort");
    String word = game.getWord();
    game.setAttemptsLeft(word.length());
    gameService.fillLetterLists(game);
    game.setUnusedLetters(new HashSet<Character>(Arrays.asList('r', 't')));
    when(gameService.getGame(anyString())).thenReturn(game);
    gameService.tryLetter("asd123", 't');
    assertThat(game.getUnusedLetters().size()).isEqualTo(1);
  }
}
