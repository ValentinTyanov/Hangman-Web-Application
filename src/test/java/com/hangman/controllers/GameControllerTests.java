package com.hangman.controllers;

import com.hangman.HangmanApplication;
import com.hangman.services.GameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = HangmanApplication.class)
public class GameControllerTests {

  @Autowired GameService gameService;

  // gets the context from our config bean
  @Autowired private WebApplicationContext wac;
  private MockMvc mockMvc;

  // executes before each test method -> makes a builder to create requests and matchers
  @BeforeEach
  public void setup() {
    DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
    this.mockMvc = builder.build();
  }

  @Test
  public void shouldReturnHomePage() throws Exception {
    ResultMatcher ok = MockMvcResultMatchers.status().isOk();

    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/");
    this.mockMvc.perform(builder).andExpect(ok);
  }

  @Test
  public void shouldCreateGameAndRedirect() throws Exception {
    ResultMatcher redirect = MockMvcResultMatchers.status().is3xxRedirection();
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/games");
    this.mockMvc.perform(builder).andExpect(redirect);
  }

  @Test
  public void shouldRetrieveGameAndShowGamePage() throws Exception {
    String gameId = gameService.createGame(null);

    ResultMatcher ok = MockMvcResultMatchers.status().isOk();

    MockHttpServletRequestBuilder builder2 = MockMvcRequestBuilders.get("/games/" + gameId);
    this.mockMvc.perform(builder2).andExpect(ok);
  }
}
