package de.david.inez.services.test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.hamcrest.collection.IsEmptyCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import de.david.inez.models.Product;
import de.david.inez.repositories.ProductRepository;
import de.david.inez.services.similarity.SimilarityService;
import de.david.inez.services.util.Similarity;
import de.david.inez.services.util.SimilarityUtil;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SimilarityServiceTest {

	@Autowired
	private SimilarityService similarityService;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Test
	@DisplayName("Example Service should work!")
	public void testGetSimilarities() {
		
		List<String> compareableList = new ArrayList<>();
		Collections.addAll(compareableList, "Milch", "Milchprodukt", "EDEKA Milch", "Messing", "Mai", "Sommer", "Schweiz");
		
		List<Similarity<String>> similarites = similarityService.getSimilarities("1l Milch", compareableList, (s) -> s);
		
		MatcherAssert.assertThat(similarites, not(IsEmptyCollection.empty()));
		
		
	}
	
	@Test
	@DisplayName("Example Service should work!")
	public void testGetSortedSimilarities() {
		
		List<String> compareableList = new ArrayList<>();
		Collections.addAll(compareableList, "Milch", "Milchprodukt", "EDEKA Milch", "Messing", "Mai", "Sommer", "Schweiz");
		
		List<Similarity<String>> similarites = similarityService.getSortedSimilarities("1l Milch", compareableList, (s) -> s);
		
		MatcherAssert.assertThat(similarites, not(IsEmptyCollection.empty()));
		
		
	}
	
	@Test
	@DisplayName("Example Service should work!")
	public void testGetHighestSimilarites() {
		
		List<String> compareableList = new ArrayList<>();
		Collections.addAll(compareableList, "Milch", "Milchprodukt", "EDEKA Milch", "Messing", "Mai", "Sommer", "Schweiz");
		
		List<Similarity<String>> similarites = similarityService.getHighestSimilarites("1l Milch", compareableList, (s) -> s, 5);
		
		MatcherAssert.assertThat(similarites, not(IsEmptyCollection.empty()));
		
		
	}
	
	@Test
	@DisplayName("testGetHighestSimilaritesWithLessCompareables")
	public void testGetHighestSimilaritesWithLessCompareables() {
		
		List<String> compareableList = new ArrayList<>();
		Collections.addAll(compareableList, "Milch", "Milchprodukt", "EDEKA Milch");
		
		List<Similarity<String>> similarites = similarityService.getHighestSimilarites("1l Milch", compareableList, (s) -> s, 5);
		
		MatcherAssert.assertThat(similarites, not(IsEmptyCollection.empty()));
		
		
	}
	
	@Test
	@DisplayName("testGetHighestSimilaritesWithLessCompareables")
	public void testGetHighestSimilaritesWithProducts() {
		
		List<Product> compareableList = new ArrayList<>();
		compareableList = productRepository.findAll();
		
		List<Similarity<Product>> similarites = similarityService.getHighestSimilarites("1l Milch", compareableList, (Product p) -> p.getName(), 5);
		
		MatcherAssert.assertThat(similarites, not(IsEmptyCollection.empty()));
		
		
	}
	
	@Test
	@DisplayName("Example Service should work!")
	public void testGetEqualStrSequences() {
		
		List<String> strSequences = SimilarityUtil.getEqualStrSequences("Milch", "Schweiz");
		
		MatcherAssert.assertThat(strSequences, hasSize(1));
		
		
	}
	
	@Test
	@DisplayName("Example Service should work!")
	public void testGenerateStrSequencesRating() {
		
		List<String> strSequences = SimilarityUtil.getEqualStrSequences("Milch", "1l Milch");
		double rating = similarityService.generateStrSequencesRating("Milch", strSequences);
		
		assertEquals(1, rating);
		
		
	}
	
}
