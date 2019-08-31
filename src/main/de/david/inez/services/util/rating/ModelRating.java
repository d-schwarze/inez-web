package de.david.inez.services.util.rating;

public class ModelRating<T> extends Rating {

	private T model;
	
	public ModelRating() {
		super();
		
	}
	
	public ModelRating(T model, double rating) {
		super(rating);
		
		this.model = model;
		
	}
	
	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}
}
