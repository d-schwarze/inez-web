package de.david.inez.services.util;

public class Similarity<T> {

	private T value;
	
	private double likeness;
	
	public Similarity() { }

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public double getLikeness() {
		return likeness;
	}

	public void setLikeness(double likeness) {
		this.likeness = likeness;
	}
}
