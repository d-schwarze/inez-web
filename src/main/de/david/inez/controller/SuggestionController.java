package de.david.inez.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.david.inez.models.Product;
import de.david.inez.models.ProductEntry;
import de.david.inez.services.SuggestionService;

@RestController
@RequestMapping("/suggestions")
public class SuggestionController {

	@Autowired
	private SuggestionService suggestionService;
	
	@GetMapping
	public List<ProductEntry> getBestSuggestedProductEntries(@RequestParam("input") String input) {
		
		return suggestionService.getBestSuggestedProductEntries(input);
		
	}
	
	@GetMapping("/all")
	public List<ProductEntry> getSuggestedProductEntries(@RequestParam("input") String input) {
		
		return suggestionService.getSuggestedProductEntries(input);
		
	}
	
}
