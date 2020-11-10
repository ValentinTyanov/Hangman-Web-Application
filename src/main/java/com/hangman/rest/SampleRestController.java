package com.hangman.rest;

import com.hangman.model.Game;
import com.hangman.model.UnusedLetter;
import com.hangman.service.GameService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SampleRestController {

  @Autowired private GameService gameService;

  @GetMapping("/games")
  public @ResponseBody List<Game> getGames() {
    return gameService.getActiveGames();
  }

  @GetMapping("/games/{gameId}")
  public Game getGame(@PathVariable String gameId) {
    return gameService.findById(gameId);
  }

  @PostMapping("/games")
  public @ResponseBody Game startGame(@RequestBody Game game) {
    return gameService.createJSONGame(game);
  }

  @PostMapping("/games/{gameId}/tries")
  public Game tryLetter(@RequestBody UnusedLetter letterEntity, @PathVariable String gameId) {
    return gameService.tryLetter(gameId, letterEntity.getLetter());
  }
}
