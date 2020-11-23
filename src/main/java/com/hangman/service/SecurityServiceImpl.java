package com.hangman.service;

import com.hangman.assets.LoginInput;
import com.hangman.model.User;
import com.hangman.repository.SecurityRepository;
import javax.transaction.Transactional;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

  @Autowired SecurityRepository securityRepository;

  @Override
  @Transactional
  public User findByEmail(String email) {
    return securityRepository.findByEmail(email);
  }

  @Override
  public ResponseEntity<Void> authenticate(LoginInput loginInput) {
    Subject subject = SecurityUtils.getSubject();

    if (!subject.isAuthenticated()) {
      UsernamePasswordToken token =
          new UsernamePasswordToken(loginInput.getEmail(), loginInput.getPassword());
      token.setRememberMe(true);
      try {
        subject.login(token);
      } catch (AuthenticationException ae) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
      }
    }
    return ResponseEntity.ok().build();
  }
}
