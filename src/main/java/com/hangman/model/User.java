package com.hangman.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {

  @Id private String userId;
  private String email;
  private String password;
}
