package de.david.inez.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.david.inez.models.Product;
import de.david.inez.services.util.Similarity;

@Service
public class AutocompleteServiceImpl implements AutocompleteService {

	@Autowired
	private SimilarityService similarityService;
	
	@Autowired
	private ProductService productService;
	
	@Override
	public List<Product> getSuggestions(String input) {
		
		List<Similarity<Product>> similarities = this.similarityService.getSimilarities(
																				input, 
																				productService.getAllProducts(), 
																				p -> p.getName());
		
		return similarities.stream().map(s -> s.getModel()).collect(Collectors.toList());
		
	}

	@Override
	public List<Product> getBestSuggestions(String input) {
		
		List<Similarity<Product>> similarities = this.similarityService.getHighestSimilarites(
																				input, 
																				productService.getAllProducts(), 
																				p -> p.getName(),
																				5);

		return similarities.stream().map(s -> s.getModel()).collect(Collectors.toList());
		
	}
}
