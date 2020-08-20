package com.hangman.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Game {

  @Id private String id;
  private int attemptsLeft;
  private String word;
  private boolean wordReveal;
  private String wordInProgress;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "game_statistic_id")
  private GameStatistic gameStatistic;

  @OneToMany(
      mappedBy = "game",
      cascade = {CascadeType.ALL})
  private List<UnusedLetter> unusedLetters;
}
