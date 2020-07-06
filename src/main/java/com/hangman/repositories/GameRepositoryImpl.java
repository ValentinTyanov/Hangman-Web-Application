package com.hangman.repositories;

import com.hangman.entities.Game;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class GameRepositoryImpl implements GameRepository {

  private List<Game> gameCollection = new ArrayList<>();

  @Override
  public void addGame(Game game) {
    gameCollection.add(game);
  }

  @Override
  public Game getGame(String id) {
    return gameCollection.stream().filter(g -> g.getId().equals(id)).findFirst().orElse(null);
  }
}
