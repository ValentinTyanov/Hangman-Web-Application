package com.hangman.rest;

import com.hangman.model.Game;
import com.hangman.model.UnusedLetter;
import com.hangman.service.GameService;
import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WebServicesRestController {

  private GameService gameService;

  @Autowired
  public WebServicesRestController(GameService gameService) {
    this.gameService = gameService;
  }

  @GetMapping("/games")
  @RequiresPermissions("games:read")
  public @ResponseBody List<Game> getGames() {
    return gameService.getActiveGames();
  }

  @GetMapping("/games/{gameId}")
  @RequiresPermissions("games:read")
  public Game getGame(@PathVariable String gameId) {
    return gameService.findById(gameId);
  }

  @PostMapping("/games")
  @RequiresPermissions("games:create")
  public @ResponseBody Game startGame(@RequestBody Game game) {
    return gameService.createJSONGame(game);
  }

  @PutMapping("/games/{gameId}/tries")
  @RequiresPermissions("games:update")
  public Game tryLetter(@RequestBody UnusedLetter letterEntity, @PathVariable String gameId) {
    return gameService.tryLetter(gameId, letterEntity.getLetter());
  }
}
