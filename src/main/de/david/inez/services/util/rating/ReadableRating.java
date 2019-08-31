package de.david.inez.services.util.rating;

public abstract class ReadableRating {

	protected double rating;
	
	public ReadableRating(double rating) {
		this.rating = rating;
		
	}

	public double getRating() {
		return rating;
	}
}
