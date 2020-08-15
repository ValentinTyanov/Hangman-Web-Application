package com.hangman.services;

import com.hangman.entities.Game;
import com.hangman.entities.Ranking;

public interface GameService {

  public String createGame(Ranking ranking);

  public Game findById(String gameId);

  public void tryLetter(String gameId, char letter);

  boolean solvedPuzzle(String id, Game game);

  boolean failedPuzzle(String id, Game game);

  void revealWord(String id);
}
