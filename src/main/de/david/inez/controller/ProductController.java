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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.david.inez.models.ListedProduct;
import de.david.inez.models.Product;
import de.david.inez.services.ProductService;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping(produces = "application/json;charset=UTF-8") 
	@ResponseBody
	public List<Product> getProducts() {
		
		return productService.getAllProducts();
		
	}
	
	@GetMapping(path = "/listed" ,produces = "application/json;charset=UTF-8") 
	@ResponseBody
	public List<ListedProduct> getListedProducts() {
		
		return productService.getListedProducts();
		
	}
	
	@GetMapping(path = "/unlisted" ,produces = "application/json;charset=UTF-8") 
	@ResponseBody
	public List<Product> getUnlistedProducts() {
		
		return productService.getUnlistedProducts();
		
	}
	
	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public void addProduct(@RequestBody Product product) {
		System.out.println(product.getUnitSystem());
		productService.addProduct(product);
		
	}
	
	@PostMapping("/listed/add")
	@ResponseStatus(HttpStatus.CREATED)
	public void addListedProduct(@RequestBody ListedProduct listedProduct) {
		
		productService.addListedProduct(listedProduct);
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteProduct(@PathVariable("id") long id) {
		
		productService.removeProduct(id);
		
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public Product getProduct(@PathVariable("id") long id) {
		
		return productService.getProduct(id);
		
	}
	
	@GetMapping(path = "/listed/{id}", produces = "application/json;charset=UTF-8") 
	@ResponseBody
	public ListedProduct getListedProduct(@PathVariable("id") long id) {
		
		return productService.getListedProduct(id);
		
	}
}
