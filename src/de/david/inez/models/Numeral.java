package de.david.inez.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Numeral {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private double numericValue;
	
	private List<String> stringValues = new ArrayList<>();
	
	private NumeralClassification numeralClassification;
	
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
}
