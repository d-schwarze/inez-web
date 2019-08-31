package de.david.inez.services.test;

import java.util.ArrayList;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.hamcrest.number.IsCloseTo.*;

import de.david.inez.services.NumberService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class NumerServiceTest {

	@Autowired
	private NumberService numberService;
	
	@Test
	@DisplayName("Testing number service")
	public void testNumberService() {
		
		double number = numberService.getNumber("100Test", new ArrayList<>());
		
		MatcherAssert.assertThat(number, closeTo(100, 0));
		
	}
}
