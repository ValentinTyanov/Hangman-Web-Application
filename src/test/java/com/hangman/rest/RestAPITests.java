package com.hangman.rest;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.hangman.model.Game;
import com.hangman.model.UnusedLetter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestAPITests {

  @Test
  public void givenUrl_whenSuccessOnGetsResponseAndJsonHasRequiredKV_thenCorrect() {
    get("/api/games/14029873-1c17-4c50-9b03-0c7dc413680f")
        .then()
        .statusCode(200)
        .assertThat()
        .body("gameStatistic.id", equalTo(5));
  }

  @Test
  public void givenURL_whenSuccessOnGetsResponseAndContainsGameWord_thenCorrect() {
    RestTemplate restTemplate = new RestTemplate();
    String URI = "http://localhost:8080/api/games/";
    ResponseEntity<String> response = restTemplate.getForEntity(URI, String.class);
    assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    assertTrue(response.getBody().contains("Pestilence"));
  }

  @Test
  public void whenRequestedPost_thenCreated() {
    Game game = new Game();
    game.setId("8bc6bdbd-2822-458c-8373-2951a08a06c2");
    game.setAttemptsLeft(5);
    game.setWord("Sceptic");
    game.setWordReveal(false);
    game.setWordInProgress("Sc____c");
    given()
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .body(game)
        .when()
        .request("POST", "/api/games/")
        .then()
        .statusCode(200);
  }

  @Test
  public void whenRequestedPost_thenCreated_RestTemplate() {
    Game g = new Game();
    g.setId("2a0edfd9-299b-48b9-a0eb-dbbea00efd5d");
    g.setAttemptsLeft(5);
    g.setWord("Stagnant");
    g.setWordReveal(false);
    g.setWordInProgress("St_____t");

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    String gameResourceUrl = "http://localhost:8080/api/games/";
    HttpEntity<Game> request = new HttpEntity<>(g, headers);

    RestTemplate restTemplate = new RestTemplate();
    Game game = restTemplate.postForObject(gameResourceUrl, request, Game.class);
    assertThat(game, is(notNullValue()));
    assertThat(game.getId(), is("2a0edfd9-299b-48b9-a0eb-dbbea00efd5d"));
  }

  @Test
  public void whenRequestedPost_AndInsertedLetter_thenUpdateAttempts() {
    RestTemplate restTemplate = new RestTemplate();
    String gameURI = "http://localhost:8080/api/games/2a0edfd9-299b-48b9-a0eb-dbbea00efd5d";
    ResponseEntity<Game> response = restTemplate.getForEntity(gameURI, Game.class);
    int attemptsBeforePOST = response.getBody().getAttemptsLeft();

    UnusedLetter l = new UnusedLetter();
    l.setLetter('g');

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<UnusedLetter> request = new HttpEntity<>(l, headers);
    String URI = gameURI + "/tries";
    restTemplate.postForObject(URI, request, Game.class);

    ResponseEntity<Game> responseAfterPOST = restTemplate.getForEntity(gameURI, Game.class);
    int attemptsAfterPOST = responseAfterPOST.getBody().getAttemptsLeft();
    assertEquals(attemptsBeforePOST - 1, attemptsAfterPOST);
  }
}
