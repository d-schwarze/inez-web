package de.david.inez.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.david.inez.models.Product;
import de.david.inez.models.ProductEntry;
import de.david.inez.models.Unit;
import de.david.inez.repositories.UnitRepository;
import de.david.inez.services.similarity.ComplexSimilarityService;
import de.david.inez.services.similarity.SimilarityService;
import de.david.inez.services.util.rating.Similarity;
import de.david.inez.services.util.rating.Suggestion;

import static de.david.inez.services.util.SuggestionUtil.*;
import static de.david.inez.services.util.RatingUtil.*;

@Service
public class SuggestionServiceImpl implements SuggestionService {
	
	@Autowired
	private ComplexSimilarityService complexSimilarityService;
	
	@Autowired
	private SimilarityService similarityService;
	
	@Autowired
	private NumberService numberService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UnitRepository unitRepository;
	
	@Override
	public List<ProductEntry> getBestSuggestedProductEntries(String input) {
		
		List<Suggestion> suggestions = getBestSuggestions(input);
		
		List<ProductEntry> productEntries = suggestions.stream()
													   .map(s -> new ProductEntry(s.getAmount(), s.getProductSimilarity().getModel(), s.getUnitSimilarity().getModel()))
													   .collect(Collectors.toList());
		
		return productEntries;
		
	}
	
	public List<ProductEntry> getSuggestedProductEntries(String input) {
		
		List<Suggestion> suggestions = getSuggestions(input);
		
		return suggestions.stream()
						  .map(s -> new ProductEntry(s.getAmount(), s.getProductSimilarity().getModel(), s.getUnitSimilarity().getModel()))
						  .collect(Collectors.toList());
		
	}
	
	@Override
	public List<Suggestion> getSuggestions(String input) {
		if(input.trim().equals("")) return new ArrayList<>();
		
		List<Product> allProducts = this.productService.getAllProducts();
		
		List<Suggestion> suggestions = this.generateSuggestions(input, allProducts, this.unitRepository.findAll());
		
		sort(suggestions);
		
		return suggestions;
		
	}
	
	@Override
	public List<Suggestion> getBestSuggestions(String input) {
		if(input.trim().equals("")) return new ArrayList<>();
		
		List<Product> allProducts = this.productService.getAllProducts();
		
		List<Suggestion> suggestions = this.generateBestSuggestions(input, allProducts, this.unitRepository.findAll());
		
		sort(suggestions);
		
		return suggestions;
		
	}

	@Override
	public List<Suggestion> getBestSuggestions(String input, List<Product> products, List<Unit> units) {
		
		List<Suggestion> suggestions = this.generateBestSuggestions(input, products, units);
		
		sort(suggestions);
		
		return suggestions;
	}
	
	@Override
	public List<Suggestion> getSuggestions(String input, List<Product> products, List<Unit> units) {
		
		List<Suggestion> suggestions = this.generateSuggestions(input, products, units);
		
		sort(suggestions);
		
		return suggestions;
		
	}
	
	private List<Suggestion> generateBestSuggestions(String input, List<Product> products, List<Unit> units) {
		List<Suggestion> bestSuggestions = new ArrayList<>();
		
		List<Similarity<Product>> productSimilarities = getBestProductSimilarities(input, products, units);
		List<Similarity<Unit>> unitSimilarities = getBestUnitSimilarities(input, units, products);
		
		List<String> externalDataForNumberRating = combine(products, units);
		double amount = numberService.getNumber(input, externalDataForNumberRating);
		
		for(Similarity<Product> sp : productSimilarities) {
			
			Suggestion s = getSuggestion(sp, unitSimilarities);
			s.setAmount(amount);
			
			bestSuggestions.add(s);
			
		}
		
		return bestSuggestions;
	}
	
	private List<Suggestion> generateSuggestions(String input, List<Product> products, List<Unit> units) {
		List<Suggestion> suggestions = new ArrayList<>();
		
		List<Similarity<Product>> productSimilarities = getProductSimilarities(input, products, units);
		List<Similarity<Unit>> unitSimilarities = getUnitSimilarities(input, units, products);
		
		List<String> externalDataForNumberRating = combine(products, units);
		double amount = numberService.getNumber(input, externalDataForNumberRating);
		
		for(Similarity<Product> sp : productSimilarities) {
			
			Suggestion s = getSuggestion(sp, unitSimilarities);
			s.setAmount(amount);
			
			suggestions.add(s);
			
		}
		
		return suggestions;
	}
	
	public Suggestion getSuggestion(Similarity<Product> productSimilarity, List<Similarity<Unit>> unitSimilarities) {
		
		Similarity<Unit> unitSimilarity = getUnitSimilarity(productSimilarity, unitSimilarities);
		
		return new Suggestion(productSimilarity, unitSimilarity);
		
	}
	
	public Similarity<Unit> getUnitSimilarity(Similarity<Product> productSimilarity, List<Similarity<Unit>> unitSimilarities) {
		if(productSimilarity.getModel().getUnitSystem() == null) return new Similarity<Unit>(null, 0.0);
		
		//Highest Rating
		Similarity<Unit> bestFittingUnitSimilarity = null;
		
		for(Similarity<Unit> us : unitSimilarities) {
			
			if(productSimilarity.getModel().getUnitSystem().containsUnit(us.getModel()) &&
				(bestFittingUnitSimilarity == null || bestFittingUnitSimilarity.getRating() < us.getRating())) {
				
				bestFittingUnitSimilarity = us;
				
			}
		}
		
		
		if(bestFittingUnitSimilarity == null)
			bestFittingUnitSimilarity = new Similarity<Unit>(productSimilarity.getModel().getUnitSystem().getBaseUnit(), 0.0);
		
		return bestFittingUnitSimilarity;
		
	}
	
	public List<Similarity<Product>> getBestProductSimilarities(String input, List<Product> products, List<Unit> units) {
		
		List<Similarity<Product>> similarities = this.similarityService.getHighestSimilaritesExtensive(
																					input, 
																					products, 
																					(Product p) -> p.getAllNames(),
																					5);

		return similarities;
		
	}
	
	public List<Similarity<Unit>> getBestUnitSimilarities(String input, List<Unit> units, List<Product> products) {
		
		List<Similarity<Unit>> bestSimilarities = this.complexSimilarityService.getHighestSimilaritesExtensiveWithInputRating(
																					input, 
																					units, 
																					u -> u.getAllNames(),
																					5,
																					products.stream().map(p -> p.getAllNames()).flatMap(List::stream).collect(Collectors.toList()));
		
		return bestSimilarities;
		
	}
	
	public List<Similarity<Product>> getProductSimilarities(String input, List<Product> products, List<Unit> units) {
		
		List<Similarity<Product>> similarities = this.similarityService.getSimilaritiesExtensive(
																					input, 
																					products, 
																					(Product p) -> p.getAllNames());

		return similarities;
		
	}
	
	public List<Similarity<Unit>> getUnitSimilarities(String input, List<Unit> units, List<Product> products) {
		
		List<Similarity<Unit>> bestSimilarities = this.complexSimilarityService.getSimilaritiesExtensiveWithInputRating(
																					input, 
																					units, 
																					u -> u.getAllNames(),
																					products.stream().map(p -> p.getAllNames()).flatMap(List::stream).collect(Collectors.toList()));
		
		return bestSimilarities;
		
	}
}
