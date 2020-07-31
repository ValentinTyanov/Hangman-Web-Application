package com.hangman.repositories;

import com.hangman.entities.Game;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.springframework.stereotype.Repository;

@Repository
public class GameRepositoryImpl implements GameRepository {

  private List<Game> gameCollection = new ArrayList<>();

  @Override
  public synchronized void addGame(Game game) {
    gameCollection.add(game);
  }

  @Override
  public synchronized Game getGame(String id) {
    return gameCollection.stream().filter(g -> g.getId().equals(id)).findFirst().orElse(null);
  }

  @Override
  public boolean exists(Predicate<Game> pred) {
    return gameCollection.stream().anyMatch(pred);
  }
}
