package de.david.inez.services.util.rating;

public class Rating extends ReadableRating {
	
	public Rating() {
		super(0.0);
		
	}
	
	public Rating(double rating) {
		super(rating);
		
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
}
