package de.david.inez.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.david.inez.models.Name;
import de.david.inez.repositories.NameRepository;

@RestController
@RequestMapping("/names")
public class NameController {
	
	@Autowired
	private NameRepository nameRepository;
	
	@CrossOrigin
	@GetMapping
	public List<Name> getProductNames() {
		
		return nameRepository.findAll();
		
	}
	
	@PostMapping
	public List<Name> getProductNamesByIds(@RequestBody List<Long> ids) {
		
		return nameRepository.findAllById(ids);
		
	}
	
	@CrossOrigin
	@GetMapping("/{id}")
	public Name getProductName(@PathVariable("id") long id) {
		
		return nameRepository.findById(id).get();
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void removeProductName(@PathVariable("id") long id) {
		
		nameRepository.deleteById(id);
		
	}
	
	
	@PostMapping(path = {"/add", "/update"})
	@ResponseStatus(HttpStatus.CREATED)
	public void addProductName(@RequestBody Name name) {
		
		nameRepository.saveAndFlush(name);
		
	}

}
