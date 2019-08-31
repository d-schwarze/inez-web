package de.david.inez.services;

import java.util.List;

import de.david.inez.models.Numeral;
import de.david.inez.services.util.StringNumber;
import de.david.inez.services.util.rating.NumberRating;
import de.david.inez.services.util.rating.Rating;

public interface NumberService {

	public double getNumber(String input, List<String> externalData);
	
	public List<NumberRating> getRatedNumbers(String input, List<String> externalData);
	
	public List<StringNumber> findNumbers(String input);
	
}
