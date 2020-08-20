package com.hangman.repository;

import com.hangman.model.Game;
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
