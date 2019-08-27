package de.david.inez.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import de.david.inez.models.Numeral;
import de.david.inez.models.NumeralClassification;
import de.david.inez.models.Unit;
import de.david.inez.repositories.NumeralRepository;
import de.david.inez.services.util.Similarity;

public class NumberServiceImpl implements NumberService {

	@Autowired
	private SimilarityService similarityService;
	
	@Autowired
	private NumeralRepository numeralRepository;
	
	@Override
	public List<Numeral> getNumerals(String value) {
		
		List<Similarity<Numeral>> similarities = similarityService.getSimiliaritiesWithMinRatingExtensive(
																		value, 
																		numeralRepository.findAll(), 
																		(Numeral n) -> n.getStringValues(), 
																		80.0);
		
		return similarities.stream().map(s -> s.getModel()).collect(Collectors.toList());
	}

	
	@Override
	public double getTotalAmount(String value) {
		
		
		
		return 0;
	}

	public List<Double> findNumbers(String value) {
		
		List<Double> numbers = new ArrayList<>();
		
		StringBuilder currentNumber = new StringBuilder();
		
		for(int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			
			if(Character.isDigit(c))
				currentNumber.append(c);
			else if(currentNumber.length() > 0) {
				
				numbers.add(Double.valueOf(currentNumber.toString()));
				
				currentNumber = new StringBuilder();
				
			}
		}
		
		if(numbers.size() == 0) throw new NoNumbersFoundException();
		
		return numbers;
		
	}
	
	public double findBestNumber(String value) {
		
		List<Double> numbers = this.findNumbers(value);
		
		numbers.sort((n1, n2) -> {
			
			int n1Evaluation = this.getNumberEvaluation(n1, value);
			int n2Evaluation = this.getNumberEvaluation(n2, value);
			
			if(n1Evaluation > n2Evaluation) return 1;
			
			if((n1Evaluation == n2Evaluation) && this.isNumberBeforeNumber(n1, n2, value)) return 1;
			
			if((n1Evaluation == n2Evaluation) && !this.isNumberBeforeNumber(n1, n2, value)) return -1;
			
			return -1;
			
		});
		
		return numbers.get(0);
		
	}
	
	public double findBestNumber(String value, Unit unit) {
		
		List<Double> numbers = this.findNumbers(value);
		
		numbers.sort((n1, n2) -> {
			
			int n1Evaluation = this.getNumberEvaluation(n1, value, unit);
			int n2Evaluation = this.getNumberEvaluation(n2, value, unit);
			
			if(n1Evaluation > n2Evaluation) return 1;
			
			if((n1Evaluation == n2Evaluation) && this.isNumberBeforeNumber(n1, n2, value)) return 1;
			
			if((n1Evaluation == n2Evaluation) && !this.isNumberBeforeNumber(n1, n2, value)) return -1;
			
			return -1;
			
		});
		
		return numbers.get(0);
	}
	
	public boolean isNumberBeforeNumber(double number, double otherNumber, String value) {
		
		if(value.indexOf(String.valueOf(number)) < value.indexOf(String.valueOf(otherNumber)))
			return true;
		
		return false;
		
	}
	
	public int getNumberEvaluation(double number, String value) {
		
		if(this.isNumberAlone(value, number)) return 2;
		
		return 1;
		
	}
	
	public int getNumberEvaluation(double number, String value, Unit unit) {
		
		if(this.isNumberAlone(value, number) && this.isNumberFollowedByUnit(value, unit, number)) return 4;
		
		if(this.isNumberStartOfLetters(value, number) && this.isNumberPartOfUnit(value, unit, number)) return 5;
		
		if(this.isNumberStartOfLetters(value, number) && this.isNumberFollowedByUnit(value, unit, number)) return 3;
		
		if(this.isNumberAlone(value, number)) return 2;
		
		return 1;
		
	}
	
	public boolean isNumberAlone(String value, double number) {
		
		for(String str : value.split(" ")) {
			
			if(str.equals(String.valueOf(number)))
				return true;
			
		}
		
		return false;
		
	}
	
	public boolean isNumberStartOfLetters(String value, double number) {
		
		for(String str : value.split(" ")) {
			
			if(str.startsWith(String.valueOf(number)))
				return true;
			
		}
		
		return false;
		
	}
	
	public boolean isNumberPartOfUnit(String value, Unit unit, double number) {
		
		int index = value.indexOf(String.valueOf(number));
		
		for(String name : unit.getNames()) {
			
			if(value.startsWith(name, index + String.valueOf(number).length()))
				return true;
			
		}
		
		return false;
		
	}
	
	public boolean isNumberFollowedByUnit(String value, Unit unit, double number) {
		
		String[] values = value.split(" ");
		
		for(int i = 0; i < values.length; i++) {
			
			String s = values[i];
			
			if(s.contains(String.valueOf(number)) && (i + 1) < values.length) {
				
				for(String name : unit.getNames()) {
					
					if(s.contains(name)) return true;
					
				}
			}
		}
		
		return false;
		
	}
	
	
	/*@Override
	public double getTotalAmount(String value) {
		
		List<Numeral> numerals = this.getNumerals(value);
		
		StringBuilder totalAmount = new StringBuilder("");
		
		for(int i = 0; i < numerals.size(); i++) {
			Numeral n = numerals.get(i);
			
			Numeral nextN = numerals.get(i + 1);
			
			if(n.getNumeralClassification() == NumeralClassification.BASE && nextN.getNumeralClassification() == NumeralClassification.POWER_OF_TEN) {
				
				
				
			}
			
		}
		
		return Double.valueOf(totalAmount.toString());
		
	}
	
	private List<Double> getPowerOfTens(List<Numeral> numerals) {
		
		for(int i = 0; i < numerals.size(); i++) {
			Numeral n = numerals.get(i);
			Numeral nextN = numerals.get(i + 1);
			
			if(n.getNumeralClassification() == NumeralClassification.BASE && nextN.getNumeralClassification() == NumeralClassification.POWER_OF_TEN) {
				
				
				
			}
			
		}
		
		return null;
		
	}*/
 
}
