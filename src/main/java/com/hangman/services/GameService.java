package com.hangman.services;

import com.hangman.entities.Game;

public interface GameService {

  public String createGame();

  public void fillLetterLists(Game game);

  public Game getGame(String gameId);

  public void tryLetter(String gameId, char letter);
}
