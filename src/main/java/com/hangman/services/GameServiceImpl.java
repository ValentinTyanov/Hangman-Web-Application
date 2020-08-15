package com.hangman.services;

import com.hangman.entities.Game;
import com.hangman.entities.GameStatistic;
import com.hangman.entities.Ranking;
import com.hangman.entities.UnusedLetter;
import com.hangman.repositories.GameRepository;
import com.hangman.repositories.RankingRepository;
import com.hangman.repositories.UnusedLetterRepositoryImpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameServiceImpl implements GameService {

  private RankingService rankingService;
  private WordService wordService;
  private GameRepository gameRepository;
  private UnusedLetterRepositoryImpl unusedLetterRepository;
  private RankingRepository rankingRepository;

  @Autowired
  public GameServiceImpl(
      WordService wordService,
      RankingService rankingService,
      GameRepository gameRepository,
      UnusedLetterRepositoryImpl unusedLetterRepository,
      RankingRepository rankingRepository) {
    this.rankingService = rankingService;
    this.wordService = wordService;
    this.gameRepository = gameRepository;
    this.unusedLetterRepository = unusedLetterRepository;
    this.rankingRepository = rankingRepository;
  }

  @Override
  @Transactional
  public String createGame(Ranking newRanking) {

    Game game = new Game();
    game.setWord(wordService.generateWord());
    setupWordInProgress(game);

    game.setAttemptsLeft(game.getWord().length());
    game.setId(UUID.randomUUID().toString());
    setupUnusedLetters(game);

    String alias = newRanking.getAlias();
    if (!alias.equals("")) {
      GameStatistic gameStatistic = new GameStatistic();
      Date date = new Date();
      gameStatistic.setDate(date);

      Ranking existingRanking = rankingRepository.findByAlias(alias);

      if (null == existingRanking) {
        gameStatistic.setRanking(newRanking);
        game.setGameStatistic(gameStatistic);
        rankingRepository.save(newRanking);
      } else {
        gameStatistic.setRanking(existingRanking);
        game.setGameStatistic(gameStatistic);
        rankingRepository.save(existingRanking);
      }
    }
    gameRepository.create(game);
    return game.getId();
  }

  public void setupWordInProgress(Game game) {
    String word = game.getWord();
    char fc = word.charAt(0);
    char lc = word.charAt(word.length() - 1);

    StringBuilder wordString = new StringBuilder();
    wordString.append(fc);

    for (int i = 1; i < word.length() - 1; i++) {
      char currentLetter = word.charAt(i);
      char outputChar =
          currentLetter == Character.toLowerCase(fc) || currentLetter == lc ? currentLetter : '_';
      wordString.append(outputChar);
    }
    wordString.append(lc);
    game.setWordInProgress(wordString.toString());
  }

  public void setupUnusedLetters(Game game) {
    Set<Character> alphabet =
        new HashSet<>(
            Arrays.asList(
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
                'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));

    String word = game.getWord().toLowerCase();
    alphabet.remove(word.charAt(0));
    alphabet.remove(word.charAt(word.length() - 1));

    List<UnusedLetter> unusedLetters = new ArrayList<>();
    for (Character letter : alphabet) {
      UnusedLetter unusedLetter = new UnusedLetter(letter);
      unusedLetter.setGame(game);
      unusedLetters.add(unusedLetter);
    }
    game.setUnusedLetters(unusedLetters);
  }

  @Override
  @Transactional
  public Game findById(String gameId) {
    return gameRepository.findById(gameId);
  }

  @Override
  @Transactional
  public void revealWord(String id) {
    Game game = gameRepository.findById(id);
    game.setRevealWord();
  }

  @Override
  @Transactional
  public void tryLetter(String id, char clickedLetter) {
    Game game = gameRepository.findById(id);
    String originalWord = game.getWord();

    boolean wordContainsClickedLetter = originalWord.indexOf(clickedLetter) != -1;
    if (wordContainsClickedLetter) {
      updateWordInProgress(clickedLetter, game, originalWord);
    }
    deleteClickedLetterButton(clickedLetter, game);

    game.setAttemptsLeft(game.getAttemptsLeft() - 1);
    gameRepository.update(game);
  }

  private void updateWordInProgress(char clickedLetter, Game game, String originalWord) {
    int[] matchingLetterIndices =
        IntStream.range(1, originalWord.length() - 1)
            .filter(index -> originalWord.charAt(index) == clickedLetter)
            .toArray();

    String wordInProgress = game.getWordInProgress();
    char[] wordInProgressChars = wordInProgress.toCharArray();

    for (int index : matchingLetterIndices) {
      wordInProgressChars[index] = clickedLetter;
    }

    String updatedWord = String.valueOf(wordInProgressChars);
    game.setWordInProgress(updatedWord);
  }

  public void deleteClickedLetterButton(char clickedLetter, Game game) {
    List<UnusedLetter> unusedLetters = game.getUnusedLetters();

    UnusedLetter letterToRemove =
        unusedLetters
            .stream()
            .filter(obj -> obj.getLetter() == clickedLetter)
            .findFirst()
            .orElse(null);

    unusedLetterRepository.deleteById(letterToRemove.getId());
  }

  @Override
  @Transactional
  public boolean solvedPuzzle(String id, Game game) {
    boolean gameEnd = gameRepository.hasSolvedPuzzle(id);
    setStatsIfEligible(id, game, gameEnd, true);
    letterButtonsCleanUp(gameEnd, game);
    return gameEnd;
  }

  @Override
  @Transactional
  public boolean failedPuzzle(String id, Game game) {
    boolean gameEnd = gameRepository.hasFailedPuzzle(id);
    setStatsIfEligible(id, game, gameEnd, false);
    letterButtonsCleanUp(gameEnd, game);
    return gameEnd;
  }

  public void setStatsIfEligible(
      String id, Game game, boolean gameEnd, boolean invokedFromSolvedPuzzle) {
    boolean hasAlias = null != game.getGameStatistic();
    if (hasAlias && gameEnd) {
      rankingService.setStats(id, invokedFromSolvedPuzzle);
    }
  }

  public void letterButtonsCleanUp(boolean gameEnd, Game game) {
    if (gameEnd) {
      unusedLetterRepository.deleteAllLettersByGameId(game);
    }
  }
}
