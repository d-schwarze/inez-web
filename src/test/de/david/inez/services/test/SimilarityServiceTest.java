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

import de.david.inez.models.Name;
import de.david.inez.models.Product;
import de.david.inez.models.Unit;
import de.david.inez.models.UnitSystem;
import de.david.inez.repositories.ProductRepository;
import de.david.inez.services.similarity.SimilarityService;
import de.david.inez.services.util.SimilarityUtil;
import de.david.inez.services.util.rating.Similarity;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SimilarityServiceTest {

	@Autowired
	private SimilarityService similarityService;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Test
	@DisplayName("Testing unit ratings.")
	public void testGetProductSimilarities() {
		UnitSystem us = new UnitSystem();
		us.setName("Volumen");
		
		Unit unit1 = new Unit();
		unit1.setId(1L);
		unit1.setFactorToBaseUnit(1.0);
		ArrayList<Name> names = new ArrayList<>();
		names.add(new Name("Liter"));
		names.add(new Name("l"));
		unit1.setNames(names);
		unit1.setPreferedName(new Name("Liter"));
		
		Unit unit2 = new Unit();
		unit1.setId(2L);
		unit2.setFactorToBaseUnit(1.0);
		names = new ArrayList<>();
		names.add(new Name("ml"));
		names.add(new Name("Millilter"));
		unit2.setNames(names);
		unit2.setPreferedName(new Name("Milliter"));
		
		us.addUnit(unit1);
		us.addUnit(unit2);
		
		
		Product p1 = new Product(new Name("EDEKA Milch"), new ArrayList<>(), null, us);
		Product p2 = new Product(new Name("Milch"), new ArrayList<>(), null, us);
		
		List<Product> products = List.of(p1, p2);
		
		List<Similarity<Product>> similarites = similarityService.getSimilaritiesExtensive("1ml Milch", products, (Product p) -> p.getAllNames());
		
		MatcherAssert.assertThat(similarites, not(IsEmptyCollection.empty()));
		
		for(Similarity<Product> s : similarites) {
			
			System.out.println("Name: " + s.getModel().getPreferedName().getSingular());
			System.out.println("Rating: " + s.getRating());
			System.out.println("----------");
			
		}
	}
	
	@Test
	@DisplayName("Testing similarity functionality")
	public void testGetSimilarities() {
		
		List<String> compareableList = new ArrayList<>();
		Collections.addAll(compareableList, "Milch", "Milchprodukt", "EDEKA Milch", "Messing", "Mai", "Sommer", "Schweiz");
		
		List<Similarity<String>> similarites = similarityService.getSimilarities("1l Milch", compareableList, (s) -> s);
		
		MatcherAssert.assertThat(similarites, not(IsEmptyCollection.empty()));
		
		for(Similarity<String> s : similarites) {
			
			System.out.println("Name: " + s.getModel());
			System.out.println("Rating: " + s.getRating());
			System.out.println("----------");
			
		}
	}
	
	@Test
	@DisplayName("Testing sorted similarities")
	public void testGetSortedSimilarities() {
		
		List<String> compareableList = new ArrayList<>();
		Collections.addAll(compareableList, "Milch", "Milchprodukt", "EDEKA Milch", "Messing", "Mai", "Sommer", "Schweiz");
		
		List<Similarity<String>> similarites = similarityService.getSortedSimilarities("1l Milch", compareableList, (s) -> s);
		
		MatcherAssert.assertThat(similarites, not(IsEmptyCollection.empty()));
		
		for(Similarity<String> s : similarites) {
			
			System.out.println("Name: " + s.getModel());
			System.out.println("Rating: " + s.getRating());
			System.out.println("----------");
			
		}
	}
	
	@Test
	@DisplayName("Testing highest similarities")
	public void testGetHighestSimilarites() {
		
		List<String> compareableList = new ArrayList<>();
		Collections.addAll(compareableList, "Milch", "Milchprodukt", "EDEKA Milch", "Messing", "Mai", "Sommer", "Schweiz");
		
		List<Similarity<String>> similarites = similarityService.getHighestSimilarites("1l Milch", compareableList, (s) -> s, 5);
		
		MatcherAssert.assertThat(similarites, hasSize(5));
		
		for(Similarity<String> s : similarites) {
			
			System.out.println("Name: " + s.getModel());
			System.out.println("Rating: " + s.getRating());
			System.out.println("----------");
			
		}
	}
	
	@Test
	@DisplayName("Testing highest similarities with less items then the limit")
	public void testGetHighestSimilaritesWithLessCompareables() {
		
		List<String> compareableList = new ArrayList<>();
		Collections.addAll(compareableList, "Milch", "Milchprodukt", "EDEKA Milch");
		
		List<Similarity<String>> similarites = similarityService.getHighestSimilarites("1l Milch", compareableList, (s) -> s, 5);
		
		MatcherAssert.assertThat(similarites, hasSize(3));
		
		for(Similarity<String> s : similarites) {
			
			System.out.println("Name: " + s.getModel());
			System.out.println("Rating: " + s.getRating());
			System.out.println("----------");
			
		}
	}
	
	@Test
	@DisplayName("Testing equal string sequences")
	public void testGetEqualStrSequences() {
		
		List<String> strSequences = SimilarityUtil.getEqualStrSequences("Milch", "Schweiz", true);
		
		MatcherAssert.assertThat(strSequences, hasSize(1));
		
		for(String s : strSequences) {
			
			System.out.println("Seq: " + s);
			System.out.println("----------");
			
		}
	}
	
}
