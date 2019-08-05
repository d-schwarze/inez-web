package de.david.inez.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.david.inez.models.ListedProduct;
import de.david.inez.models.Product;
import de.david.inez.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository<Product> productRepository;
	
	@Autowired
	private ProductRepository<ListedProduct> listedProductRepository;
	
	@Override
	public List<Product> getAllProducts() {
		
		return productRepository.findAll();
		
	}

	@Override
	public List<Product> getUnlistedProducts() {
		
		List<Product> allProducts = productRepository.findAll();
		
		allProducts.removeIf(p -> p instanceof ListedProduct);
		
		return allProducts;
		
	}

	@Override
	public List<ListedProduct> getListedProducts() {
		
		return listedProductRepository.findAll();
		
	}

	@Override
	public void addProduct(Product product) {
		
		productRepository.save(product);
		
		
	}

	@Override
	public void addListedProduct(ListedProduct listedProduct) {
		
		listedProductRepository.save(listedProduct);
		
	}

	@Override
	public void removeProduct(long id) {
		
		productRepository.deleteById(id);
		
	}

	@Override
	public void updateProduct(Product product) {

		productRepository.save(product);
		
	}

	@Override
	public void updateListedProduct(ListedProduct listedProduct) {
		
		listedProductRepository.save(listedProduct);
		
	}

	@Override
	public Product getProduct(long id) {
		
		return productRepository.findById(id).get();
		
	}

	@Override
	public ListedProduct getListedProduct(long id) {
		
		return listedProductRepository.findById(id).get();
		
	}
}
