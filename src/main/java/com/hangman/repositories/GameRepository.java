package com.hangman.repositories;

import com.hangman.entities.Game;

public interface GameRepository {

  public Game getGame(String id);

  public void addGame(Game game);
}
