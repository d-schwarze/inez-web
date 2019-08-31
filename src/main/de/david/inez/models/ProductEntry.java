package de.david.inez.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ProductEntry extends Entry {

	@OneToOne
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;
	
	private double amount;
	
	@OneToOne
	@JoinColumn(name="UNIT_ID")
	private Unit unit;
	
	
	public ProductEntry() {	
		
	}
	
	public ProductEntry(Product product, Unit unit) {
		
		this.product = product;
		this.unit = unit;
		
	}
	
	public ProductEntry(double amount, Product product, Unit unit) {
		this(product, unit);
		
		this.amount = amount;
		
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}
}
