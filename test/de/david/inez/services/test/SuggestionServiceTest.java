package de.david.inez.services.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.david.inez.services.SuggestionServiceImpl;

public class SuggestionServiceTest {
	
	@Test
	@DisplayName("Example Service should work!")
	void exampleServiceShouldWork() {
		var suggestionService = new SuggestionServiceImpl();

		assertEquals("Hello, JUnit 5\n", "test");
	}
	
}
