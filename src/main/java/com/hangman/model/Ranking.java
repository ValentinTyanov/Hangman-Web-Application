package com.hangman.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ranking")
public class Ranking {

  @Id
  @Column(name = "alias")
  private String alias;

  @Column(name = "high_score")
  private int highScore;

  @OneToMany(
      mappedBy = "ranking",
      cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
  private List<GameStatistic> gameStatistics;

  public Ranking() {}

  public Ranking(String alias) {
    this.alias = alias;
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public int getHighScore() {
    return highScore;
  }

  public void setHighScore(int highScore) {
    this.highScore = highScore;
  }

  public List<GameStatistic> getGameStatistics() {
    return gameStatistics;
  }

  public void setGameStatistics(List<GameStatistic> gameStatistics) {
    this.gameStatistics = gameStatistics;
  }
}
