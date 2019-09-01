package de.david.inez.services.test;

import static org.hamcrest.CoreMatchers.not;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import de.david.inez.models.Name;
import de.david.inez.models.Unit;
import de.david.inez.services.similarity.ComplexSimilarityService;
import de.david.inez.services.util.rating.Similarity;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ComplexSimilarityServiceTest {

	@Autowired
	private ComplexSimilarityService complexSimilarityService;
	
	
	@Test
	@DisplayName("Testing product ratings.")
	public void testGetProductSimilarities() {
		
		Unit unit1 = new Unit();
		unit1.setFactorToBaseUnit(1.0);
		ArrayList<Name> names = new ArrayList<>();
		names.add(new Name("Liter"));
		names.add(new Name("l"));
		unit1.setNames(names);
		unit1.setPreferedName(new Name("Liter"));
		
		Unit unit2 = new Unit();
		unit2.setFactorToBaseUnit(1.0);
		names = new ArrayList<>();
		names.add(new Name("ml"));
		names.add(new Name("Millilter"));
		unit2.setNames(names);
		unit2.setPreferedName(new Name("Milliter"));
		
		List<Unit> compareableList = new ArrayList<>();
		Collections.addAll(compareableList, unit1, unit2);
		
		
		List<String> externalData = new ArrayList<>();
		Collections.addAll(externalData, "Milch", "Milchprodukt", "EDEKA Milch", "Messing", "Mai", "Sommer", "Schweiz");
		
		List<Similarity<Unit>> similarites = complexSimilarityService.getSimilaritiesExtensiveWithInputRating("1ml Milch", compareableList, (Unit u) -> u.getAllNames(), externalData);
		
		MatcherAssert.assertThat(similarites, not(IsEmptyCollection.empty()));
		
		for(Similarity<Unit> s : similarites) {
			
			System.out.println("Name: " + s.getModel().getPreferedName().getSingular());
			System.out.println("Rating: " + s.getRating());
			System.out.println("----------");
			
		}
	}
	
}
