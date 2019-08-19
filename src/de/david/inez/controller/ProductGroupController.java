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

import de.david.inez.models.ProductGroup;
import de.david.inez.repositories.ProductGroupRepository;

@RestController
@RequestMapping("/productgroups")
public class ProductGroupController {

	@Autowired
	private ProductGroupRepository productGroupRepository;
	
	@CrossOrigin
	@GetMapping
	public List<ProductGroup> getProductGroups() {
		
		return productGroupRepository.findAll();
		
	}
	
	@CrossOrigin
	@GetMapping("/{id}")
	public ProductGroup getProductGroup(@PathVariable("id") long id) {
		
		return productGroupRepository.findById(id).get();
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void removeProductGroup(@PathVariable("id") long id) {
		
		productGroupRepository.deleteById(id);
		
	}
	
	@CrossOrigin
	@PostMapping(path = {"/add", "/update"})
	@ResponseStatus(HttpStatus.CREATED)
	public void addProductGroup(@RequestBody ProductGroup productGroup) {
		System.out.println(productGroup.getDesignation());
		productGroupRepository.save(productGroup);
		
	}
}
