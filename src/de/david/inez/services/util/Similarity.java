package de.david.inez.services.util;

public class Similarity<T> {

	private T model;
	
	private double rating;
	
	public Similarity() { }

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}


	public T getModel() {
		return model;
	}


	public void setModel(T model) {
		this.model = model;
	}
}
