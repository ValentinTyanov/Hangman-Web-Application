package com.hangman.rest;

import com.hangman.assets.TemporaryRanking;
import com.hangman.model.Ranking;
import com.hangman.service.GameServiceImpl;
import com.hangman.service.RankingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/react-api")
public class GameRestController {

  private GameServiceImpl gameService;
  private RankingService rankingService;

  @Autowired
  public GameRestController(GameServiceImpl gameService, RankingService rankingService) {
    this.gameService = gameService;
    this.rankingService = rankingService;
  }

  @GetMapping("/topten-ever")
  public List<Ranking> getTopTenEver() {
    return rankingService.getTopTen();
  }

  @GetMapping("/topten-last-month")
  public List<TemporaryRanking> getTopTenLastMonth() {
    return rankingService.getTopTenLastMonthAsList();
  }

  @PostMapping("/start-game")
  public String startGame(@RequestBody Ranking ranking) {
    String gameId = gameService.createGame(ranking);
    return gameId;
  }
}
