package de.david.inez.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Unit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	/**
	 * Formula/Logic: baseUnit/otherUnit or baseUnit/thisUnit
	 * E.g: 1Liter is 1000ml oder 0.5Liter is 500ml. Which means, the factor is 1000.
	 * 1/1000 is 0.001
	 * E.g: Gramm is the base unit: 1g is 0.001kg -> 1/0.001 -> 0.001 is the factor for kg.
	 */
	private double factorToBaseUnit;
	
	@ElementCollection
	@CollectionTable(name="UnitNames", joinColumns=@JoinColumn(name="id"))
	@Column(name="name")
	private List<Name> names = new ArrayList<>();
	
	@OneToOne
	private Name preferedName;
	
	public Unit() { }
	
	public Unit(Name preferedName, double factorToBaseUnit, Name... names) {
		
		this.setPreferedName(preferedName);
		this.setFactorToBaseUnit(factorToBaseUnit);
		this.setNames(Arrays.asList(names));
		
	}

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

	public List<Name> getNames() {
		return names;
	}

	public void setNames(List<Name> names) {
		
		this.names = names;
		
		//if(this.preferedName != null && !this.names.contains(this.preferedName))
			//this.names.add(this.preferedName);
	}

	public Name getPreferedName() {
		return preferedName;
	}

	public void setPreferedName(Name preferedName) {
		if(!this.names.contains(preferedName))
			this.names.add(preferedName);
		
		this.preferedName = preferedName;
	}
	
	@Override
	public boolean equals(Object otherUnit) {
		
		if(!(otherUnit instanceof Unit)) return false;
		
		if(otherUnit == this) return true;
		
		if(((Unit) otherUnit).getId() == this.id) return true;
		
		return false;
		
	}
	
	public List<String> getAllNames() {
		
		List<String> names = new ArrayList<>();
		
		for(Name pn : this.names) {
			
			if(!names.contains(pn.getSingular())) names.add(pn.getSingular());
			
			if(!names.contains(pn.getPlural())) names.add(pn.getPlural());
			
		}
		
		return names;
		
	}
}
