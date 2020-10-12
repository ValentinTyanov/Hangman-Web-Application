package com.hangman.service;

import com.hangman.model.Game;
import com.hangman.model.Ranking;
import java.util.List;

public interface GameService {

  public String createGame(Ranking ranking);

  public Game findById(String gameId);

  public Game tryLetter(String gameId, char letter);

  boolean solvedPuzzle(String id, Game game);

  boolean failedPuzzle(String id, Game game);

  void revealWord(String id);

  List<Game> getActiveGames();

  Game createJSONGame(Game game);
}
