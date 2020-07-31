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

  @GetMapping("/games/{gameid}")
  public String showGame(Model model, @PathVariable String gameid) {
    Game game = gameService.getGame(gameid);
    model.addAttribute("game", game);

    if (gameService.solvedPuzzle(gameid)) {
      return "victory";
    }
    if (gameService.failedPuzzle(gameid)) {
      return "gameOver";
    }

    return "gamePage";
  }

  @PostMapping("/games/{gameid}")
  public String tryLetter(Model theModel, @PathVariable String gameid, @RequestParam char letter) {
    gameService.tryLetter(gameid, letter);
    return "redirect:/games/{gameid}";
  }

  @PostMapping("/games/{gameid}/real-word")
  public String revealWord(Model theModel, @PathVariable String gameid) {
    Game game = gameService.getGame(gameid);
    game.setWordReveal(game.getWord());
    return "redirect:/games/{gameid}";
  }
}
