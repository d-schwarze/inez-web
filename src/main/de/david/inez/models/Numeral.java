package de.david.inez.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;

@Entity
public class Numeral {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private double numericValue;
	
	@ElementCollection
	@CollectionTable(name="NumeralStringValues", joinColumns=@JoinColumn(name="id"))
	@Column(name="stringvalue")
	private List<String> stringValues = new ArrayList<>();
	
	private NumeralClassification numeralClassification;
	
	@Transient
	private String foundValue;
	
	public Numeral() { }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getNumericValue() {
		return numericValue;
	}

	public void setNumericValue(double numericValue) {
		this.numericValue = numericValue;
	}

	public List<String> getStringValues() {
		return stringValues;
	}

	public void setStringValues(List<String> stringValues) {
		this.stringValues = stringValues;
	}

	public NumeralClassification getNumeralClassification() {
		return numeralClassification;
	}

	public void setNumeralClassification(NumeralClassification numeralClassification) {
		this.numeralClassification = numeralClassification;
	}

	public String getFoundValue() {
		return foundValue;
	}

	public void setFoundValue(String foundValue) {
		this.foundValue = foundValue;
	}
}
