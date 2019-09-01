package de.david.inez.services.test;

import static org.hamcrest.CoreMatchers.not;

import java.util.ArrayList;
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

import de.david.inez.models.Name;
import de.david.inez.models.Product;
import de.david.inez.models.ProductEntry;
import de.david.inez.models.ProductGroup;
import de.david.inez.models.Unit;
import de.david.inez.models.UnitSystem;
import de.david.inez.repositories.ProductGroupRepository;
import de.david.inez.repositories.ProductRepository;
import de.david.inez.repositories.UnitRepository;
import de.david.inez.repositories.UnitSystemRepository;
import de.david.inez.services.SuggestionService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SuggestionServiceTest {
	
	@Autowired
	private SuggestionService suggestionService;
	
	@MockBean
	private ProductRepository productRepository;
	
	@MockBean
	private UnitRepository unitRepository;
	
	@MockBean
	private UnitSystemRepository unitSystemRepository;
	
	@MockBean
	private ProductGroupRepository productGroupRepository;
	
	
	@Test
	@DisplayName("Testing Suggestions")
	void testSuggestions() {
		
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
		us.setBaseUnit(unit1);
		
		
		ProductGroup pg = new ProductGroup("Kühlregal");
		pg.setId(1L);
		
		
		Product p1 = new Product();
		p1.setId(1L);
		p1.setPreferedName(new Name("Milch"));
		p1.setProductGroup(pg);
		p1.setUnitSystem(us);
		
		Product p2 = new Product();
		p2.setId(2L);;
		p2.setPreferedName(new Name("EDEKA Milch"));
		p2.setUnitSystem(us);
		p2.setProductGroup(pg);
		
		List<ProductEntry> suggestions = suggestionService.getBestSuggestedProductEntries("1ml Milch");
		
		MatcherAssert.assertThat(suggestions, not(IsEmptyCollection.empty()));
		
	}
	
	@Test
	@DisplayName("Testing prefered names")
	void testPreferedNames() {
		
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
		us.setBaseUnit(unit1);
		
		
		ProductGroup pg = new ProductGroup("Kühlregal");
		pg.setId(1L);
		
		
		Product p1 = new Product();
		p1.setId(1L);
		p1.setPreferedName(new Name("Cola"));
		p1.setSynonyms(List.of(new Name("Milch")));
		p1.setProductGroup(pg);
		p1.setUnitSystem(us);
		
		Product p2 = new Product();
		p2.setId(2L);;
		p2.setPreferedName(new Name("EDEKA Milch"));
		p2.setUnitSystem(us);
		p2.setProductGroup(pg);
		
		List<ProductEntry> suggestions = suggestionService.getBestSuggestedProductEntries("Milch");
		
		MatcherAssert.assertThat(suggestions.get(0).getName(), equals("Cola"));
		
	}
}
