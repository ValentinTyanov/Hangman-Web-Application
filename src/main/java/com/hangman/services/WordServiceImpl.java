package com.hangman.services;

import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class WordServiceImpl implements WordService {

  private static final String[] WORDS = {
    "Arson",
    "Theft",
    "Magic",
    "Candle",
    "Bacteria",
    "Caramel",
    "Hospital",
    "Aquarium",
    "Cemetery",
    "Patriarchy",
    "Significant",
    "Trespasser",
    "Terminator",
    "Spectacular",
    "Quicksort",
    "Pestilence",
    "Synergy",
    "Maleficent",
    "Javascript",
    "Python",
    "Swift",
    "Angular",
    "Spring",
    "Linear"
  };

  @Override
  public String generateWord() {
    int index = new Random().nextInt(WORDS.length);
    return WORDS[index];
  }
}
