package com.hangman.controller;

import com.hangman.model.Game;
import com.hangman.model.Ranking;
import com.hangman.service.GameServiceImpl;
import com.hangman.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {

  private GameServiceImpl gameService;
  private RankingService rankingService;

  @Autowired
  public GameController(GameServiceImpl gameService, RankingService rankingService) {
    this.gameService = gameService;
    this.rankingService = rankingService;
  }

  @GetMapping("/")
  public String showPage(Model model) {
    model.addAttribute("ranking", new Ranking());
    model.addAttribute("rankingService", rankingService);
    return "home";
  }

  @PostMapping("/games")
  public String createGame(@ModelAttribute("ranking") Ranking ranking) {
    String gameId = gameService.createGame(ranking);
    return "redirect:/games/" + gameId;
  }

  @GetMapping("/games/{gameId}")
  public String showGame(Model model, @PathVariable String gameId) {
    Game game = gameService.findById(gameId);
    model.addAttribute("game", game);
    model.addAttribute("rankingService", rankingService);

    if (gameService.solvedPuzzle(gameId, game)) {
      return "victory";
    }
    if (gameService.failedPuzzle(gameId, game)) {
      return "gameOver";
    }

    return "gamePage";
  }

  @PostMapping("/ranking")
  public String showRanking(Model model, @RequestParam String choice) {
    model.addAttribute("rankingService", rankingService);
    return rankingService.rankPage(choice);
  }

  @PostMapping("/games/{gameId}")
  public String tryLetter(@PathVariable String gameId, @RequestParam char letter) {
    gameService.tryLetter(gameId, letter);
    return "redirect:/games/{gameId}";
  }

  @PostMapping("/games/{gameId}/real-word")
  public String revealWord(@PathVariable String gameId) {
    gameService.revealWord(gameId);
    return "redirect:/games/{gameId}";
  }
}
