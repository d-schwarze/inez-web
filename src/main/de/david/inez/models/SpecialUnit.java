package de.david.inez.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class SpecialUnit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	@OneToOne
	@JoinColumn(name = "COMPAREABLE_UNIT_ID")
	private Unit compareableUnit;
	
	private double factor;
	
	public SpecialUnit() { }

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

	public Unit getCompareableUnit() {
		return compareableUnit;
	}

	public void setCompareableUnit(Unit compareableUnit) {
		this.compareableUnit = compareableUnit;
	}

	public double getFactor() {
		return factor;
	}

	public void setFactor(double factor) {
		this.factor = factor;
	}
	
	@Override
	public boolean equals(Object otherSpecialUnit) {
		
		if(!(otherSpecialUnit instanceof SpecialUnit)) return false;
		
		if(otherSpecialUnit == this) return true;
		
		if(((SpecialUnit) otherSpecialUnit).getId() == this.id) return true;
		
		return false;
		
	}
}
