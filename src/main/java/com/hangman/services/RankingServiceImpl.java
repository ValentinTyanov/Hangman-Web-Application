package com.hangman.services;

import com.hangman.entities.Game;
import com.hangman.entities.GameStatistic;
import com.hangman.entities.Ranking;
import com.hangman.repositories.GameRepository;
import com.hangman.repositories.GameStatisticRepository;
import com.hangman.repositories.GameStatisticSpecifications;
import com.hangman.repositories.RankingRepository;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RankingServiceImpl implements RankingService {

  private GameRepository gameRepository;
  private RankingRepository rankingRepository;
  private GameStatisticRepository gameStatisticRepository;

  @Autowired
  public RankingServiceImpl(
      GameRepository gameRepository,
      RankingRepository rankingRepository,
      GameStatisticRepository gameStatisticRepository) {
    this.gameRepository = gameRepository;
    this.rankingRepository = rankingRepository;
    this.gameStatisticRepository = gameStatisticRepository;
  }

  @Override
  public void setStats(String id, boolean victory) {
    Game game = gameRepository.findById(id);
    int attemptsLeft = game.getAttemptsLeft();
    int score = victory ? attemptsLeft + 1 : attemptsLeft;
    boolean hasUsedHint = game.getRevealWord();
    if (!hasUsedHint && victory) {
      score += 2;
    }

    GameStatistic gameStatistic = game.getGameStatistic();
    gameStatistic.setScore(score);
    gameStatistic.setHasUsedHint(hasUsedHint);

    updateHighScore(score, gameStatistic);

    gameRepository.update(game);
  }

  public void updateHighScore(int score, GameStatistic gameStatistic) {
    Ranking ranking = gameStatistic.getRanking();
    int currentHighScore = ranking.getHighScore();
    if (score > currentHighScore) {
      currentHighScore = score;
      ranking.setHighScore(currentHighScore);
    }
    rankingRepository.save(ranking);
  }

  public List<Ranking> getTopTen() {
    return rankingRepository.findTop10ByOrderByHighScoreDesc();
  }

  @Transactional
  public Map<String, GameStatistic> getTopTenLastMonth() {
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DATE, -30);
    Date monthAgo = new Date(cal.getTimeInMillis());

    List<GameStatistic> scoresFromLast30Days =
        gameStatisticRepository.findAll(GameStatisticSpecifications.highScoresSince(monthAgo));

    return top10DistinctPlayers(scoresFromLast30Days);
  }

  public Map<String, GameStatistic> top10DistinctPlayers(List<GameStatistic> scoresFromLast30Days) {
    Map<String, GameStatistic> distinctPlayerScoresFromLast30Days = new LinkedHashMap<>();

    for (GameStatistic gameStatistic : scoresFromLast30Days) {
      String alias = gameStatistic.getRanking().getAlias();
      distinctPlayerScoresFromLast30Days.putIfAbsent(alias, gameStatistic);
    }

    Map<String, GameStatistic> top10DistinctPlayerScoresFromLast30Days = new LinkedHashMap<>();

    int count = 0;
    for (Map.Entry<String, GameStatistic> entry : distinctPlayerScoresFromLast30Days.entrySet()) {
      if (count++ == 10) {
        break;
      }
      top10DistinctPlayerScoresFromLast30Days.put(entry.getKey(), entry.getValue());
    }
    return top10DistinctPlayerScoresFromLast30Days;
  }

  @Override
  public String rankPage(String choice) {
    return choice.equals("all-time") ? "topTenEver" : "topTenLastMonth";
  }
}
