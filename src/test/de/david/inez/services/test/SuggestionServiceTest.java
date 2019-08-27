package de.david.inez.services.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

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
import de.david.inez.services.SimilarityService;
import de.david.inez.services.SuggestionService;
import de.david.inez.services.SuggestionServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SuggestionServiceTest {
	
	@MockBean
	private ProductService productService;
	
	@MockBean
	private SimilarityService similarityService;
	
	@MockBean
	private ProductRepository productRepository;
	
	@MockBean
	private ListedProductRepository listedProductRepository;
	
	@Autowired
	private SuggestionService suggestionService;
	
	@Test
	@DisplayName("Example Service should work!")
	void exampleServiceShouldWork() {
		
		List<Product> products = suggestionService.getBestSuggestions("Milch");
		
		System.out.println(products.size());
		
	}
	
}
