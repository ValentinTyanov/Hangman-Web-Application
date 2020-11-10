package com.hangman.service;

import com.hangman.assets.TemporaryRanking;
import com.hangman.model.GameStatistic;
import com.hangman.model.Ranking;
import java.util.List;
import java.util.Map;

public interface RankingService {

  void setStats(String id, boolean victory);

  String rankPage(String choice);

  List<Ranking> getTopTen();

  Map<String, GameStatistic> getTopTenLastMonth();

  List<TemporaryRanking> getTopTenLastMonthAsList();
}
