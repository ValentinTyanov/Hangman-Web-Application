package com.hangman.controllers;

import com.hangman.entities.Game;
import com.hangman.services.GameServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {

  private GameServiceImpl gameService;

  @Autowired
  public GameController(GameServiceImpl gameService) {
    this.gameService = gameService;
  }

  @GetMapping("/")
  public String showPage() {
    return "home";
  }

  @PostMapping("/games")
  public String createGame() {
    String gameId = gameService.createGame();
    return "redirect:/games/" + gameId;
  }

  @GetMapping("/games/{gameId}")
  public String showGame(Model model, @PathVariable String gameId) {
    Game game = gameService.findById(gameId);
    model.addAttribute("game", game);

    if (gameService.solvedPuzzle(gameId)) {
      return "victory";
    }
    if (gameService.failedPuzzle(gameId)) {
      return "gameOver";
    }

    return "gamePage";
  }

  @PostMapping("/games/{gameId}")
  public String tryLetter(Model theModel, @PathVariable String gameId, @RequestParam char letter) {
    gameService.tryLetter(gameId, letter);
    return "redirect:/games/{gameId}";
  }

  @PostMapping("/games/{gameId}/real-word")
  public String revealWord(Model theModel, @PathVariable String gameId) {
    gameService.revealWord(gameId);
    return "redirect:/games/{gameId}";
  }
}
