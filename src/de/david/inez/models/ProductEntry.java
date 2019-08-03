package de.david.inez.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ProductEntry extends Entry {

	@OneToOne
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;
	
	public ProductEntry() {	
		
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
