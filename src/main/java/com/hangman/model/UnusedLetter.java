package com.hangman.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class UnusedLetter {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private char letter;

  @ManyToOne(
      cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
  @JoinColumn(name = "game_id")
  private Game game;
}
