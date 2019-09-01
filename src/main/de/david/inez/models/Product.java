package de.david.inez.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne
	private Name preferedName;
	
	@OneToMany
	private List<Name> synonyms = new ArrayList<>();
	
	@OneToOne
	@JoinColumn(name="PRODUCTGROUP_ID")
	private ProductGroup productGroup;
	
	@OneToOne
	@JoinColumn(name="UNITSYSTEM_ID")
	private UnitSystem unitSystem;

	public Product() {
		
	}
	
	public Product(Name preferedName, List<Name> synonyms, ProductGroup productGroup, UnitSystem unitSystem) {
		this.setPreferedName(preferedName);
		this.setProductGroup(productGroup);
		this.setUnitSystem(unitSystem);
		this.setSynonyms(synonyms);
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ProductGroup getProductGroup() {
		return productGroup;
	}

	public void setProductGroup(ProductGroup productGroup) {
		this.productGroup = productGroup;
	}
	
	public UnitSystem getUnitSystem() {
		return unitSystem;
	}

	public void setUnitSystem(UnitSystem unitSystem) {
		this.unitSystem = unitSystem;
	}

	public Name getPreferedName() {
		return preferedName;
	}

	public void setPreferedName(Name preferedName) {
		this.preferedName = preferedName;
	}

	public List<Name> getSynonyms() {
		return synonyms;
	}

	public void setSynonyms(List<Name> synonyms) {
		this.synonyms = synonyms;
	}
	
	public List<String> getAllNames() {
		
		List<String> names = new ArrayList<>();
		
		for(Name pn : this.synonyms) {
			
			if(!names.contains(pn.getSingular())) names.add(pn.getSingular());
			
			if(!names.contains(pn.getPlural())) names.add(pn.getPlural());
			
		}
		
		if(!names.contains(preferedName.getSingular())) names.add(preferedName.getSingular());
		
		if(!names.contains(preferedName.getPlural())) names.add(preferedName.getPlural());
		
		return names;
		
	}
	
}
