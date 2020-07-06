package com.hangman.entities;

import java.util.List;
import java.util.Set;

public class Game {

  private String id;

  private int attemptsLeft;

  private String word;

  private List<Character> originalLetterList;

  private List<Character> hiddenLetterList;

  private Set<Character> unusedLetters;

  private String wordReveal = "Reveal Word";

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

  public List<Character> getOriginalLettersList() {
    return originalLetterList;
  }

  public void setOriginalLettersList(List<Character> letters) {
    this.originalLetterList = letters;
  }

  public List<Character> getHiddenLettersList() {
    return hiddenLetterList;
  }

  public void setHiddenLettersList(List<Character> hiddenLetterList) {
    this.hiddenLetterList = hiddenLetterList;
  }

  public Set<Character> getUnusedLetters() {
    return unusedLetters;
  }

  public void setUnusedLetters(Set<Character> unusedLetters) {
    this.unusedLetters = unusedLetters;
  }

  public String getWordReveal() {
    return wordReveal;
  }

  public void setWordReveal(String wordReveal) {
    this.wordReveal = wordReveal;
  }
}
