package com.hangman.service;

import com.hangman.model.Game;
import com.hangman.model.Ranking;

public interface GameService {

  public String createGame(Ranking ranking);

  public Game findById(String gameId);

  public void tryLetter(String gameId, char letter);

  boolean solvedPuzzle(String id, Game game);

  boolean failedPuzzle(String id, Game game);

  void revealWord(String id);
}
