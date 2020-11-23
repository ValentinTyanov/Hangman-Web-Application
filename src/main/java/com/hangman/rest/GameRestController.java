package com.hangman.rest;

import com.hangman.assets.LoginInput;
import com.hangman.assets.TemporaryRanking;
import com.hangman.model.Ranking;
import com.hangman.service.GameService;
import com.hangman.service.RankingService;
import com.hangman.service.SecurityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/react-api")
public class GameRestController {

  private GameService gameService;
  private RankingService rankingService;
  private SecurityService securityService;

  @Autowired
  public GameRestController(
      GameService gameService, RankingService rankingService, SecurityService securityService) {
    this.gameService = gameService;
    this.rankingService = rankingService;
    this.securityService = securityService;
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
  public ResponseEntity<String> startGame(@RequestBody Ranking ranking) {
    String gameId = gameService.createGame(ranking);
    return ResponseEntity.ok(gameId);
  }

  @PostMapping("/authentication")
  public ResponseEntity<Void> authenticate(@RequestBody LoginInput loginInput) {
    return securityService.authenticate(loginInput);
  }
}
