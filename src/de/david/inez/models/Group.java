package de.david.inez.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "DB_GROUP")
public class Group {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	
	public Group() { }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}





/*
public enum Group2 {

	VEGETABLES_FRUITS, WINE, REFRIGERATED_SHELF, MEAT, LAUNDRY, DRINKS, WASHING
	
}*/
