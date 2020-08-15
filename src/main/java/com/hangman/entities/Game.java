package com.hangman.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "game")
public class Game {

  @Id
  @Column(name = "id")
  private String id;

  @Column(name = "attempts_left")
  private int attemptsLeft;

  @Column(name = "word")
  private String word;

  @Column(name = "word_reveal")
  private boolean revealWord;

  @Column(name = "word_in_progress")
  private String wordInProgress;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "game_statistic_id")
  private GameStatistic gameStatistic;

  @OneToMany(
      mappedBy = "game",
      cascade = {CascadeType.ALL})
  private List<UnusedLetter> unusedLetters;

  public Game() {}

  public Game(String id, int attemptsLeft, String word) {
    this.id = id;
    this.attemptsLeft = attemptsLeft;
    this.word = word;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getAttemptsLeft() {
    return attemptsLeft;
  }

  public void setAttemptsLeft(int attemptsLeft) {
    this.attemptsLeft = attemptsLeft;
  }

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public boolean getRevealWord() {
    return revealWord;
  }

  public void setRevealWord() {
    this.revealWord = true;
  }

  public String getWordInProgress() {
    return wordInProgress;
  }

  public void setWordInProgress(String wordInProgress) {
    this.wordInProgress = wordInProgress;
  }

  public GameStatistic getGameStatistic() {
    return gameStatistic;
  }

  public void setGameStatistic(GameStatistic gameStatistic) {
    this.gameStatistic = gameStatistic;
  }

  public List<UnusedLetter> getUnusedLetters() {
    return unusedLetters;
  }

  public void setUnusedLetters(List<UnusedLetter> unusedLetters) {
    this.unusedLetters = unusedLetters;
  }
}
