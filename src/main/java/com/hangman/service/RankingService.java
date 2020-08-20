package com.hangman.service;

public interface RankingService {

  void setStats(String id, boolean victory);

  public String rankPage(String choice);
}
