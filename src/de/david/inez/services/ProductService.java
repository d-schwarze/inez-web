package de.david.inez.services;

import java.util.List;

import de.david.inez.models.ListedProduct;
import de.david.inez.models.Product;

public interface ProductService {

	public List<Product> getAllProducts();
	
	public List<Product> getUnlistedProducts();
	
	public List<ListedProduct> getListedProducts();
	
	public void addProduct(Product product);
	
	public void addListedProduct(ListedProduct listedProduct);
	
	public void removeProduct(long id);
	
	public void updateProduct(Product product);
	
	public void updateListedProduct(ListedProduct listedProduct);
	
	public Product getProduct(long id);
	
	public ListedProduct getListedProduct(long id);
	
}
