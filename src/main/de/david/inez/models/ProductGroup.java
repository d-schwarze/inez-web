package de.david.inez.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductGroup {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String designation;
	
	public ProductGroup() { }

	public ProductGroup(String designation) {
		
		this.setDesignation(designation);
		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}
}





/*
public enum Group2 {

	VEGETABLES_FRUITS, WINE, REFRIGERATED_SHELF, MEAT, LAUNDRY, DRINKS, WASHING
	
}*/
