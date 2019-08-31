package de.david.inez.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.david.inez.models.Numeral;
import de.david.inez.repositories.NumeralRepository;
import de.david.inez.services.similarity.ComplexSimilarityService;
import de.david.inez.services.util.IndexedNumeral;
import de.david.inez.services.util.SimilarityUtil;
import de.david.inez.services.util.StringNumber;
import de.david.inez.services.util.rating.NumberRating;

import static de.david.inez.services.util.RatingUtil.*;

@Service
public class NumberServiceImpl implements NumberService {

	
	@Autowired
	private NumeralRepository numeralRepository;
	
	@Autowired
	private ComplexSimilarityService complexSimilarityService;
	
	@Override
	public double getNumber(String input, List<String> externalData) {
		List<IndexedNumeral> indexedNumerals = getIndexedNumerals(input);
		List<NumberRating> ratedNumbers = getRatedNumbers(input, externalData);
		
		NumberRating bestNumber = getBestStringNumber(ratedNumbers);
		
		if((bestNumber == null || bestNumber.getRating() < 25) && indexedNumerals.size() == 0) return 1; 
		
		double number = combine(bestNumber, indexedNumerals);
		
		return number;
		
	}
	
	private double combine(NumberRating bestNumber, List<IndexedNumeral> indexedNumerals) {
		
		sortIndexedNumerals(indexedNumerals);
		
		double number = 1.0;
		int index = 0;
		
		if(bestNumber != null) {
			
			number = Double.parseDouble(bestNumber.getModel().getValue());
			index = bestNumber.getModel().getIndex() + bestNumber.getModel().getValue().length() + 1;
			
		} else {
			
			index = indexedNumerals.get(0).getIndex();
			
		}
		
		for(IndexedNumeral in : indexedNumerals) {
			
			if(in.getIndex() == index) {
				
				number *= in.getNumeral().getNumericValue();
				
				index = in.getIndex() + in.getNumeral().getFoundValue().length();
				
			}
			
		}
		
		return number;
	}
	
	private NumberRating getBestStringNumber(List<NumberRating> ratedNumbers) {
		
		sort(ratedNumbers);
		
		if(ratedNumbers.size() != 0)
			return ratedNumbers.get(0);
		else
			return null;
		
	}

	public List<IndexedNumeral> getIndexedNumerals(String input) {
		
		List<Numeral> numerals = numeralRepository.findAll();
		
		List<IndexedNumeral> indexedNumerals = new ArrayList<>();
		
		for(Numeral n : numerals) {
			
			String containedValue = SimilarityUtil.containsAny(input, n.getStringValues());
			
			if(containedValue != null) {
				
				IndexedNumeral in = new IndexedNumeral(n, input.indexOf(containedValue));
				in.getNumeral().setFoundValue(containedValue);
				
				indexedNumerals.add(in);
				
			}
			
		}
		
		return indexedNumerals;
	}
	
	
	@Override
	public List<NumberRating> getRatedNumbers(String input, List<String> externalData) {
		
		double[] inputRating = complexSimilarityService.getInputRating(input, externalData);
		
		List<StringNumber> numbers = findNumbers(input);
		
		List<NumberRating> ratedStringNumbers = getRatings(numbers, input, inputRating);
		
		return ratedStringNumbers;
		
		
	}
	
	@Override
	public List<StringNumber> findNumbers(String input) {
		
		List<StringNumber> numbers = new ArrayList<>();
		
		StringBuilder currentNumberValue = new StringBuilder();
		int currentNumberIndex = 0;
		
		String[] letters = input.split("");
		
		for(int i = 0; i < letters.length; i++) {
			String letter = letters[i];
			
			if(isNumeric(letter)) { 
				
				currentNumberValue.append(letter); 
				
			} else if(isMathLetter(letter)) {
				
				currentNumberValue.append(letter);
				
			} else if(isNumeric(currentNumberValue.toString())) {
				
				numbers.add(new StringNumber(currentNumberValue.toString(), currentNumberIndex));
				
				currentNumberValue = new StringBuilder();
				currentNumberIndex = i + 1;
				
			} else {
				
				currentNumberValue = new StringBuilder();
				currentNumberIndex = i + 1;
				
			}
		}
		
		return numbers;
		
		
	}
	
	public List<NumberRating> getRatings(List<StringNumber> numbers, String input, double[] inputRating) {
		
		List<NumberRating> ratings = new ArrayList<>();
		
		for(StringNumber number : numbers) {
			
			ratings.add(getRating(number, input, inputRating));
			
		}
		
		return ratings;
		
	}
	
	public NumberRating getRating(StringNumber number, String input, double[] inputRating) {
		
		double rating = getRatingWithInputRating(number, inputRating);
		
		rating *= getRatingByPosition(number, inputRating.length);
		
		rating *= getRatingByLettersAround(number, input);
		
		rating *= 100.0;
		
		return new NumberRating(number, rating); 
		
	}
	
	public double getRatingWithInputRating(StringNumber number, double[] inputRating) {
		
		double rating = 1.0;
		
		for(int i = number.getIndex(); i < (number.getIndex() + number.getValue().length()); i++) {
			
			rating *= inputRating[i];
			
		}
		
		return rating;
		
	}
	
	public double getRatingByPosition(StringNumber number, int inputLength) {
		
		int index = number.getIndex();
		double rating = 0.0;
		
		//Quadratic function -> if at start or end higher rating.
		
		double centerIndex = (double) (inputLength - 1) / 2;
		
		if(index < centerIndex) {
			
			rating = Math.pow((1 - (double) index / centerIndex), 2);
			
		} else {
			
			rating = Math.pow((1 - (double) (inputLength - index - 1) / centerIndex), 2);
			
		}
		
		return rating;
		
	}
	
	public double getRatingByLettersAround(StringNumber number, String input) {
		
		double rating = 1.0;
		
		//Whitespace after (*1.2) or letter (*0.9)
		if((number.getIndex() + number.getValue().length()) < (input.length() - 1)  && input.charAt(number.getIndex() + number.getValue().length()) == ' ') 
			rating *= 1.2;
		else
			rating *= 0.9;
		
		return rating;
		
	}
	
	
	public static boolean isNumeric(String str) {
	
		return str.matches("\\d+(\\.\\d+)?") || 
			   str.matches("\\d+\\.") || 
			   str.matches("\\.\\d+") || 
			   str.matches("\\d+(\\,\\d+)?") || 
			   str.matches("\\d+\\,") || 
			   str.matches("\\,\\d+");
		
		
		
	}
	
	public static boolean isMathLetter(String letter) {
		
		switch(letter) {
		
			case ".": return true;
			case ",": return true;
			
			default: return false;
		
		}
	}
	
	public static void sortIndexedNumerals(List<IndexedNumeral> indexedNumerals) {
		
		indexedNumerals.sort((IndexedNumeral m1, IndexedNumeral m2) -> {
			
			if(m1.getIndex() > m2.getIndex()) return 1;
			
			if(m1.getIndex() < m2.getIndex()) return -1;
			
			return 0;
			
		});
	}
}
