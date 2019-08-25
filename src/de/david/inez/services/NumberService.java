package de.david.inez.services;

import java.util.List;

import de.david.inez.models.Numeral;

public interface NumberService {

	public List<Numeral> getNumerals(String value);
	
	public double getTotalAmount(String value);
	
}
