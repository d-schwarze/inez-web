package de.david.inez.services;

import java.util.List;

import de.david.inez.models.Product;

public interface AutocompleteService {
	
	public List<Product> getSuggestions(String input);
	
	public List<Product> getBestSuggestions(String input);	
	
}
