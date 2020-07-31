package com.hangman.services;

import com.hangman.entities.Game;
import com.hangman.repositories.GameRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

  private WordService wordService;
  private GameRepository gameRepository;

  @Autowired
  public GameServiceImpl(WordService wordService, GameRepository gameRepository) {
    this.wordService = wordService;
    this.gameRepository = gameRepository;
  }

  @Override
  public String createGame() {
    Game game = new Game();
    game.setWord(wordService.generateWord());
    game.setAttemptsLeft(game.getWord().length());
    game.setId(UUID.randomUUID().toString());
    fillLetterLists(game);
    Set<Character> alphabet =
        new HashSet<>(
            Arrays.asList(
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
                'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
    String word = game.getWord().toLowerCase();
    alphabet.remove(word.charAt(0));
    alphabet.remove(word.charAt(word.length() - 1));
    game.setUnusedLetters(alphabet);
    gameRepository.addGame(game);
    return game.getId();
  }

  @Override
  public void fillLetterLists(Game game) {
    fillOriginalLetterList(game);
    fillHiddenLetterList(game);
  }

  public void fillOriginalLetterList(Game game) {
    List<Character> originalLetterList = new ArrayList<>();
    String word = game.getWord();
    IntStream.range(0, word.length()).forEach(index -> originalLetterList.add(word.charAt(index)));
    game.setOriginalLettersList(originalLetterList);
  }

  public void fillHiddenLetterList(Game game) {
    List<Character> hiddenLetterList = new ArrayList<>();
    String word = game.getWord();
    char fc = word.charAt(0);
    char lc = word.charAt(word.length() - 1);
    hiddenLetterList.add(fc);

    for (int i = 1; i < word.length() - 1; i++) {
      char currentLetter = game.getOriginalLettersList().get(i);
      char outputChar = currentLetter == fc + 32 || currentLetter == lc ? currentLetter : '_';
      hiddenLetterList.add(outputChar);
    }
    hiddenLetterList.add(lc);
    game.setHiddenLettersList(hiddenLetterList);
  }

  @Override
  public Game getGame(String gameId) {
    return gameRepository.getGame(gameId);
  }

  @Override
  public void tryLetter(String id, char letter) {
    Game game = getGame(id);

    Character currentLetter =
        game.getOriginalLettersList().stream().filter(l -> l == letter).findFirst().orElse(null);

    // finds the indices where the letter resides in the word and stores them in an array
    if (currentLetter != null) {
      int[] indices =
          IntStream.range(0, game.getOriginalLettersList().size())
              .filter(index -> game.getOriginalLettersList().get(index).equals(currentLetter))
              .toArray();

      for (int index : indices) {
        game.getHiddenLettersList().set(index, currentLetter);
      }
    }
    game.setAttemptsLeft(game.getAttemptsLeft() - 1);
    game.getUnusedLetters().remove(Character.valueOf(letter));
  }

  public boolean solvedPuzzle(String id) {
    Predicate<Game> isSolved =
        g -> g.getId().equals(id) && !(g.getHiddenLettersList().contains('_'));
    return gameRepository.exists(isSolved);
  }

  public boolean failedPuzzle(String id) {
    Predicate<Game> hasFailed = g -> g.getId().equals(id) && g.getAttemptsLeft() <= 0;
    return gameRepository.exists(hasFailed);
  }
}
