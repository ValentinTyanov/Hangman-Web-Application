package com.hangman.service;

import com.hangman.model.Game;
import com.hangman.model.GameStatistic;
import com.hangman.model.Ranking;
import com.hangman.repository.GameRepository;
import com.hangman.repository.GameStatisticRepository;
import com.hangman.repository.GameStatisticSpecifications;
import com.hangman.repository.RankingRepository;
import java.time.LocalDate;
import java.util.Iterator;
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
    LocalDate monthAgo = LocalDate.now().minusDays(30);
    List<GameStatistic> scoresFromLast30Days =
        gameStatisticRepository.findAll(GameStatisticSpecifications.highScoresSince(monthAgo));

    return top10DistinctPlayers(scoresFromLast30Days);
  }

  public Map<String, GameStatistic> top10DistinctPlayers(List<GameStatistic> scoresFromLast30Days) {
    Map<String, GameStatistic> top10DistinctPlayerScoresFromLast30Days = new LinkedHashMap<>();

    Iterator<GameStatistic> iter = scoresFromLast30Days.iterator();

    while (iter.hasNext() && top10DistinctPlayerScoresFromLast30Days.size() < 10) {
      GameStatistic gameStatistic = iter.next();
      String alias = gameStatistic.getRanking().getAlias();
      top10DistinctPlayerScoresFromLast30Days.putIfAbsent(alias, gameStatistic);
    }
    return top10DistinctPlayerScoresFromLast30Days;
  }

  @Override
  public String rankPage(String choice) {
    return choice.equals("all-time") ? "topTenEver" : "topTenLastMonth";
  }
}
