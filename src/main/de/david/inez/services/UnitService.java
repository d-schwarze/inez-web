package de.david.inez.services;

import de.david.inez.models.Unit;

public interface UnitService {

	public double recognizeAmount(String value);
	
	public Unit recognizeUnit(String value);
	
	
}
