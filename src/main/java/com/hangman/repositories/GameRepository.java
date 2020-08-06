package com.hangman.repositories;

import com.hangman.entities.Game;
import java.util.List;

public interface GameRepository {

  public List<Game> findAll();

  public Game findById(String id);

  public void create(Game game);

  public void update(Game game);

  public void deleteById(String id);

  boolean hasSolvedPuzzle(String id);

  boolean hasFailedPuzzle(String id);
}
