package com.hangman.config;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

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
    filter.addPathDefinition("/api/**", "authcBasic");
    filter.addPathDefinition("/games/**/real-word", "authc");
    return filter;
  }

  @Bean
  @ConditionalOnMissingBean
  public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(
      DefaultSecurityManager securityManager) {
    // This is to enable Shiro's security annotations
    AuthorizationAttributeSourceAdvisor sourceAdvisor = new AuthorizationAttributeSourceAdvisor();
    sourceAdvisor.setSecurityManager(securityManager);
    return sourceAdvisor;
  }

  @ConditionalOnMissingBean
  @Bean(name = "defaultAdvisorAutoProxyCreator")
  @DependsOn("lifecycleBeanPostProcessor")
  public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
    DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator =
        new DefaultAdvisorAutoProxyCreator();
    defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
    return defaultAdvisorAutoProxyCreator;
  }

  @Bean
  public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
    return new LifecycleBeanPostProcessor();
  }
}
