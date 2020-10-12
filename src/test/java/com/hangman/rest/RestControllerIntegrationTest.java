package com.hangman.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hangman.HangmanApplication;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = HangmanApplication.class)
public class RestControllerIntegrationTest {

  @Autowired private WebApplicationContext wac;
  private MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Test
  public void givenURI_whenMockMVCGET_thenSuccess() throws Exception {
    this.mockMvc.perform(get("/api/games")).andDo(print()).andExpect(status().isOk());
  }

  @Test
  public void givenURI_whenMockMVC_thenVerifyResponse() throws Exception {
    MvcResult mvcResult =
        this.mockMvc
            .perform(get("/api/games/{gameId}", "13842e62-6be1-4e93-8488-f7b15e6c1e34"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.word").value("Hatchet"))
            .andReturn();

    Assert.assertEquals("application/json", mvcResult.getResponse().getContentType());
  }

  @Test
  public void givenURI_whenMockMVCPOST_thenSuccess() throws Exception {
    Object obj =
        new Object() {
          public String id = "13842e62-6be1-4e93-3477-f7b15e6c1b79";
          public int attemptsLeft = 5;
          public String word = "Seldom";
          public boolean wordReveal = false;
          public String wordInProgress = "S____m";
        };

    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(obj);

    this.mockMvc
        .perform(post("/api/games").contentType(MediaType.APPLICATION_JSON).content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value("13842e62-6be1-4e93-3477-f7b15e6c1b79"));
  }

  @Test
  public void givenURI_whenMockMVC_POSTLetter_thenUpdate() throws Exception {
    Object obj =
        new Object() {
          public char letter = 't';
        };
    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(obj);

    this.mockMvc
        .perform(
            post("/api/games/{gameId}/tries", "461bbe99-ef99-4b8b-893e-42a8ea55a7b8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.wordInProgress").value("Spect_c___r"));
  }
}
