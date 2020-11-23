package com.hangman.config;

import com.hangman.model.User;
import com.hangman.service.SecurityService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomRealm extends JdbcRealm {

  @Autowired SecurityService securityService;

  private static final String INVALID_CREDENTIALS = "Invalid username or password.";

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
    UsernamePasswordToken userToken = (UsernamePasswordToken) token;

    String email = userToken.getPrincipal().toString();
    String inputPassword = new String(userToken.getPassword());

    User user = securityService.findByEmail(userToken.getPrincipal().toString());

    if (email.isEmpty() || user == null || !user.getPassword().equals(inputPassword)) {
      throw new AuthenticationException(INVALID_CREDENTIALS);
    }

    return new SimpleAuthenticationInfo(
        userToken.getUsername(), userToken.getPassword(), getName());
  }
}
