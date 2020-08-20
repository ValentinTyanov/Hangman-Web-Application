package com.hangman.repository;

import com.hangman.model.Game;
import java.util.List;

public interface LetterRepository<T> {

  public List<T> findAll();

  public T findById(int id);

  public void add(T letter);

  public void update(T letter);

  public void deleteById(int id);

  void deleteAllLettersByGameId(Game game);
}
