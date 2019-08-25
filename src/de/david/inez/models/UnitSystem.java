package de.david.inez.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

public class UnitSystem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Unit> units;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<SpecialUnit> specialUnits;
	
	@OneToOne
	@JoinColumn(name = "BASE_UNIT_ID")
	private Unit baseUnit;
	
	public UnitSystem() {
		
	}
	
	public void addUnit(Unit unit) {
		
		if(!this.units.contains(unit))
			this.units.add(unit);
		
	}
	
	public void addSpecialUnit(SpecialUnit specialUnit) {
		
		if(!this.specialUnits.contains(specialUnit))
			this.specialUnits.add(specialUnit);
		
	}

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

	public List<Unit> getUnits() {
		return units;
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
	}

	public List<SpecialUnit> getSpecialUnits() {
		return specialUnits;
	}

	public void setSpecialUnits(List<SpecialUnit> specialUnits) {
		this.specialUnits = specialUnits;
	}
}
