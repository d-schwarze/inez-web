package de.david.inez.services.test;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import de.david.inez.models.Product;
import de.david.inez.repositories.ListedProductRepository;
import de.david.inez.repositories.ProductRepository;
import de.david.inez.services.ProductService;
import de.david.inez.services.SuggestionService;
import de.david.inez.services.SuggestionServiceImpl;
import de.david.inez.services.similarity.SimilarityService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SuggestionServiceTest {
	
	@Autowired
	private SuggestionService suggestionService;
	
	@Test
	@DisplayName("Example Service should work!")
	void testGetBestSuggestions() {
		
		List<Product> products = suggestionService.getBestSuggestions("1l Milch");
		
		MatcherAssert.assertThat(products, not(IsEmptyCollection.empty()));
		
	}
	
	@Test
	@DisplayName("Example Service should work!")
	void testGetSuggestions() {
		
		List<Product> products = suggestionService.getSuggestions("1l Milch");
		
		MatcherAssert.assertThat(products, not(IsEmptyCollection.empty()));
		
	}
	
}
