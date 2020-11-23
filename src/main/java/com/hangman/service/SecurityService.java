package com.hangman.service;

import com.hangman.assets.LoginInput;
import com.hangman.model.User;
import org.springframework.http.ResponseEntity;

public interface SecurityService {

  public User findByEmail(String email);

  ResponseEntity<Void> authenticate(LoginInput loginInput);
}
