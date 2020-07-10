package com.hangman.integration;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({WinTest.class, LoseTest.class, AttemptReductionTest.class})
public class IntegrationSuite {}
