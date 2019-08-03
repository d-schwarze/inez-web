package de.david.inez.models;

import javax.persistence.Entity;

@Entity
public class ListedProduct extends Product {
	
	private double prize;

	public double getPrize() {
		return prize;
	}

	public void setPrize(double prize) {
		this.prize = prize;
	}
}
