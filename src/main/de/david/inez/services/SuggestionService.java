package de.david.inez.services;

import java.util.List;

import de.david.inez.models.Product;
import de.david.inez.models.ProductEntry;
import de.david.inez.models.Unit;
import de.david.inez.services.util.rating.Suggestion;

public interface SuggestionService {
	
	public List<Suggestion> getSuggestions(String input);
	
	public List<Suggestion> getSuggestions(String input, List<Product> products, List<Unit> units);
	
	public List<Suggestion> getBestSuggestions(String input);
	
	public List<Suggestion> getBestSuggestions(String input, List<Product> products, List<Unit> units);
	
	public List<ProductEntry> getSuggestedProductEntries(String input);
	
	public List<ProductEntry> getBestSuggestedProductEntries(String input);
	
}
