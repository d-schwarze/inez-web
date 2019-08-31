package de.david.inez.services.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import de.david.inez.models.Product;
import de.david.inez.models.Unit;
import de.david.inez.services.util.rating.Suggestion;

public class SuggestionUtil {

	public static void sort(List<Suggestion> suggestions) {
		
		suggestions.sort((Suggestion s1, Suggestion s2) -> {
			
			if(s1.getRating() < s2.getRating()) return 1;
			
			if(s1.getRating() > s2.getRating()) return -1;
			
			return 0;
			
		});
	}
	
	public static List<String> combine(List<Product> products, List<Unit> units) {
		
		return Stream.concat(products.stream()
					                 .map(p -> p.getAllNames())
					                 .flatMap(List::stream),
							 units.stream()
						 	      .map(u -> u.getAllNames())
							 	  .flatMap(List::stream))
				     .collect(Collectors.toList());
		
	}
	
}
