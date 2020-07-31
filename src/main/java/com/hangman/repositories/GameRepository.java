package com.hangman.repositories;

import com.hangman.entities.Game;
import java.util.function.Predicate;

public interface GameRepository {

  public Game getGame(String id);

  public void addGame(Game game);

  public boolean exists(Predicate<Game> pred);
}
