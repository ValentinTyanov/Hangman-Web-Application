package com.hangman.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class GameStatistic {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private int score;
  private boolean hint;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate gameDate;

  @OneToOne(
      mappedBy = "gameStatistic",
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  private Game game;

  @ManyToOne(cascade = {CascadeType.REFRESH})
  @JoinColumn(name = "ranking_alias")
  private Ranking ranking;
}
