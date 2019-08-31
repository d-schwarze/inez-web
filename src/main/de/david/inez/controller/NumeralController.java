package de.david.inez.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.david.inez.models.Numeral;
import de.david.inez.repositories.NumeralRepository;

@RestController
@RequestMapping("/numerals")
public class NumeralController {

	@Autowired
	private NumeralRepository numeralRepository;
	
	@GetMapping
	public List<Numeral> getNumerals() {
		
		return numeralRepository.findAll();
		
	}
	
	@PostMapping
	public List<Numeral> getNumeralsByIds(@RequestBody List<Long> ids) {
		
		return numeralRepository.findAllById(ids);
		
	}
	
	@GetMapping("/{id}")
	public Numeral getNumeral(@PathVariable("id") long id) {
		
		return numeralRepository.findById(id).get();
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void removeNumeral(@PathVariable("id") long id) {
		
		numeralRepository.deleteById(id);
		
	}
	
	@PostMapping(path = {"/add", "/update"})
	@ResponseStatus(HttpStatus.CREATED)
	public void addNumeral(@RequestBody Numeral numeral) {
		
		numeralRepository.save(numeral);
		
	}
}
