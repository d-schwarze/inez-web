package de.david.inez.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.david.inez.models.Product;
import de.david.inez.services.AutocompleteService;

@RestController
@RequestMapping("/autocomplete")
public class AutoCompleteController {

	@Autowired
	private AutocompleteService autoCompleteService;
	
	@GetMapping
	public List<Product> getBestSuggestedProducts(@RequestParam("input") String input) {
		
		return autoCompleteService.getBestSuggestions(input);
		
	}
	
	@GetMapping("/all")
	public List<Product> getSuggestedProducts(@RequestParam("input") String input) {
		
		return autoCompleteService.getSuggestions(input);
		
	}
}
