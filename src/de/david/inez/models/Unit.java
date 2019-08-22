package de.david.inez.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Unit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	/**
	 * Formula/Logic: baseUnit/otherUnit or baseUnit/thisUnit
	 * E.g: 1Liter is 1000ml oder 0.5Liter is 500ml. Which means, the factor is 1000.
	 * 1/1000 is 0.001
	 * E.g: Gramm is the base unit: 1g is 0.001kg -> 1/0.001 -> 0.001 is the factor for kg.
	 */
	private double factorToBaseUnit;
	
	private List<String> names;
	
	private String preferedName;
	
	public Unit() { }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getFactorToBaseUnit() {
		return factorToBaseUnit;
	}

	public void setFactorToBaseUnit(double factorToBaseUnit) {
		this.factorToBaseUnit = factorToBaseUnit;
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		if(this.preferedName != null && !names.contains(this.preferedName))
			names.add(this.preferedName);
		
		this.names = names;
	}

	public String getPreferedName() {
		return preferedName;
	}

	public void setPreferedName(String preferedName) {
		if(!this.names.contains(preferedName))
			this.names.add(preferedName);
		
		this.preferedName = preferedName;
	}
}
