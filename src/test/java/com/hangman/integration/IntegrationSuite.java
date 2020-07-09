package com.hangman.integration;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({WinTest.class, LoseTest.class})
public class IntegrationSuite {}
