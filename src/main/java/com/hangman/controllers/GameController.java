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

  @Autowired private GameServiceImpl theGameService;

  @GetMapping("/")
  public String showPage() {
    return "home";
  }

  @PostMapping("/games")
  public String createGame() {
    String gameId = theGameService.createGame();
    return "redirect:/games/" + gameId;
  }

  @GetMapping("/games/{gameid}")
  public String showGame(Model theModel, @PathVariable String gameid) {
    Game game = theGameService.getGame(gameid);
    theModel.addAttribute("game", game);

    if (!game.getHiddenLettersList().contains('_')) {
      return "victory";
    }
    if (game.getAttemptsLeft() <= 0) {
      return "gameOver";
    }

    return "gamePage";
  }

  @PostMapping("/games/{gameid}")
  public String setLetter(Model theModel, @PathVariable String gameid, @RequestParam char letter) {
    theGameService.setLetter(gameid, letter);
    return "redirect:/games/{gameid}";
  }

  @PostMapping("/games/{gameid}/real-word")
  public String showWord(Model theModel, @PathVariable String gameid) {
    Game game = theGameService.getGame(gameid);
    theGameService.getGame(gameid).setWordReveal(game.getWord());
    return "redirect:/games/{gameid}";
  }
}
