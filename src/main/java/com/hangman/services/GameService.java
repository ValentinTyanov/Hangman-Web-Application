package com.hangman.services;

import com.hangman.entities.Game;

public interface GameService {

  public String createGame();

  public Game findById(String gameId);

  public void tryLetter(String gameId, char letter);

  boolean solvedPuzzle(String id);

  boolean failedPuzzle(String id);

  void revealWord(String id);
}
