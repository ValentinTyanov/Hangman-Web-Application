package com.hangman.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

  @JsonIgnoreProperties("ranking")
  @OneToMany(
      mappedBy = "ranking",
      cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
  private List<GameStatistic> gameStatistics;
}
