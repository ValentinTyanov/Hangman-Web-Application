package com.hangman.services;

public interface RankingService {

  void setStats(String id, boolean victory);

  public String rankPage(String choice);
}
