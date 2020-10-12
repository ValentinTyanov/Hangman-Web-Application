package com.hangman.service;

import com.hangman.model.GameStatistic;
import com.hangman.model.Ranking;
import java.util.List;
import java.util.Map;

public interface RankingService {

  void setStats(String id, boolean victory);

  public String rankPage(String choice);

  List<Ranking> getTopTen();

  Map<String, GameStatistic> getTopTenLastMonth();
}
