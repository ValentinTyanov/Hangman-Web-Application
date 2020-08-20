package com.hangman.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Ranking {

  @Id private String alias;
  private int highScore;

  @OneToMany(
      mappedBy = "ranking",
      cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
  private List<GameStatistic> gameStatistics;
}
