package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class MainTest {

	@TestConfiguration
	static class TestConfig {

	}

	@Test
	public void doTest() {
		assertTrue( true );
	}
}

