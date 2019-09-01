package de.david.inez.services;

import java.util.List;

import de.david.inez.services.util.StringNumber;
import de.david.inez.services.util.rating.NumberRating;

public interface NumberService {

	public double getNumber(String input, List<String> externalData);
	
	public List<NumberRating> getRatedNumbers(String input, List<String> externalData);
	
	public List<StringNumber> findNumbers(String input);
	
}
