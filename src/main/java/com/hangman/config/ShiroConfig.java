package com.hangman.config;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {

  @Bean
  public Realm realm() {
    return new CustomRealm();
  }

  @Bean
  public DefaultSecurityManager securityManager() {
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    securityManager.setRealm(realm());
    return securityManager;
  }

  @Bean
  public ShiroFilterChainDefinition filterChainDefinition() {
    DefaultShiroFilterChainDefinition filter = new DefaultShiroFilterChainDefinition();
    filter.addPathDefinition("/games/**/real-word", "authc");
    return filter;
  }
}
