package de.david.inez.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.david.inez.models.Product;
import de.david.inez.models.Unit;
import de.david.inez.repositories.UnitRepository;
import de.david.inez.services.similarity.ComplexSimilarityService;
import de.david.inez.services.similarity.SimilarityService;
import de.david.inez.services.util.ExternalRatingSet;
import de.david.inez.services.util.Similarity;

@Service
public class SuggestionServiceImpl implements SuggestionService {

	@Autowired
	private SimilarityService similarityService;
	
	@Autowired
	private ComplexSimilarityService complexSimilarityService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UnitRepository unitRepository;
	
	@Override
	public List<Product> getSuggestions(String input) {
		
		List<Similarity<Product>> similarities = this.similarityService.getSimilarities(
																				input, 
																				this.productService.getAllProducts(), 
																				p -> p.getName());
		
		return similarities.stream().map(s -> s.getModel()).collect(Collectors.toList());
		
	}

	@Override
	public List<Product> getBestSuggestions(String input) {
		
		List<Similarity<Product>> similarities = this.similarityService.getHighestSimilarites(
																				input, 
																				this.productService.getAllProducts(), 
																				p -> p.getName(),
																				5);

		return similarities.stream().map(s -> s.getModel()).collect(Collectors.toList());
		
	}
	
	public List<Product> getProductSuggestions(String input) {
		
		List<Similarity<Product>> similarities = this.similarityService.getHighestSimilarites(
																				input, 
																				this.productService.getAllProducts(), 
																				p -> p.getName(),
																				10);

		return similarities.stream().map(s -> s.getModel()).collect(Collectors.toList());
		
	}
	
	public List<Unit> getBestUnitSuggestions(String input, List<Unit> unitSuggestions, List<Product> productSuggestions) {
		
		List<Similarity<Unit>> bestSimilarities = this.complexSimilarityService.getHighestSimilaritesExtensiveWithInputRating(
																				input, 
																				unitSuggestions, 
																				(Unit u) -> u.getNames(),
																				5,
																				productSuggestions.stream().map(p -> p.getName()).collect(Collectors.toList()));
		
		return null;
		
	}
	
	public List<Unit> getUnitSuggestions(String input) {
		
		List<Similarity<Unit>> similarities = this.similarityService.getHighestSimilaritesExtensive(
																				input, 
																				this.unitRepository.findAll(), 
																				(Unit u) -> u.getNames(),
																				10);

		return similarities.stream().map(s -> s.getModel()).collect(Collectors.toList());
		
	}
	
	public List<Unit> getBestUnitSuggestions(String input) {
		
		List<Similarity<Unit>> similarities = this.similarityService.getHighestSimilaritesExtensive(
																				input, 
																				this.unitRepository.findAll(), 
																				(Unit u) -> u.getNames(),
																				10);

		return similarities.stream().map(s -> s.getModel()).collect(Collectors.toList());
		
	}
	
}
