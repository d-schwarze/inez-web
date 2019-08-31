package de.david.inez.services.util.rating;

import de.david.inez.models.Product;
import de.david.inez.models.Unit;
import de.david.inez.models.UnitSystem;

public class Suggestion extends ReadableRating {
	
	private double amount;
	
	private Similarity<Unit> unitSimilarity;
	
	private Similarity<Product> productSimilarity;
	
	public Suggestion(double amount, Similarity<Product> productSimilarity, Similarity<Unit> unitSimilarity) {
		super(productSimilarity.getRating() + unitSimilarity.getRating());
		
		this.amount = amount;
		this.productSimilarity = productSimilarity;
		this.unitSimilarity = unitSimilarity;
		
	}
	
	public Suggestion(Similarity<Product> productSimilarity, Similarity<Unit> unitSimilarity) {
		super(productSimilarity.getRating() + unitSimilarity.getRating());
		
		this.productSimilarity = productSimilarity;
		this.unitSimilarity = unitSimilarity;
		
	}
	
	public Suggestion(Similarity<Product> productSimilarity) {
		super(productSimilarity.getRating() + 0);
		
		this.productSimilarity = productSimilarity;		
		this.unitSimilarity = new Similarity<Unit>();
		
		if(this.productSimilarity.getModel().getUnitSystem() != null)
			this.unitSimilarity.setModel(this.productSimilarity.getModel().getUnitSystem().getBaseUnit());
		
		this.unitSimilarity.setRating(0.0);
		
	}
	
	public Similarity<Unit> getUnitSimilarity() {
		return unitSimilarity;
	}

	public void setUnitSimilarity(Similarity<Unit> unitSimilarity) {
		this.unitSimilarity = unitSimilarity;
		
		this.updateRating();
	}

	public Similarity<Product> getProductSimilarity() {
		return productSimilarity;
	}

	public void setProductSimilarity(Similarity<Product> productSimilarity) {
		this.productSimilarity = productSimilarity;
		
		this.updateRating();
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	private void updateRating() {
		
		rating = productSimilarity.getRating() + unitSimilarity.getRating();
		
	}
}
