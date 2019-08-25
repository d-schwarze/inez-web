package de.david.inez.services;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import de.david.inez.services.util.Similarity;

public class SimilarityServiceImpl implements SimilarityService {

	@Override
	public <T> List<Similarity<T>> getSimilarities(String value, List<T> compareableList,
			Function<T, String> getCompareableValue) {
		
		for(T model : compareableList) {
			
			String compareableValue = getCompareableValue.apply(model);
			
		}
		
		return null;
	}

	@Override
	public <T> List<Similarity<T>> getHighestSimilarites(String value, List<T> compareableList,
			Supplier<T> compareableValue, int maxAmount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Similarity<T> getSimiliarty(String value, String compareableValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(String value, String compareableValue) {

		return value.equals(compareableValue);
		
	}

	@Override
	public int getAmountOfEqualLetters(String value, String compareableValue) {
		
		int amountOfEqualLetters = 0;
		
		for(String s : compareableValue.split("")) {
			
			if(value.contains(s)) amountOfEqualLetters++;
			
		}
		
		return amountOfEqualLetters;
		
	}

	@Override
	public String getLongestEqualStrSequence(String value, String compareableValue) {
		
		List<String> strSequences = getEqualStrSequences(value, compareableValue);
		
		return null;
	}

	@Override
	public List<String> getEqualStrSequences(String value, String compareableValue) {
		
		List<String> strSequences = new ArrayList<>();
		
		for(int i = 0; i < compareableValue.length(); i++) {
			
			strSequences.addAll(this.getPossibleStrSequences(i, compareableValue, value));
			
		}
		
		this.removeDuplicates(strSequences);
		
		return strSequences;
		
	}
	
	public List<String> getPossibleStrSequences(int indexCompareableValue, String compareableValue, String value) {
		
		List<String> strSequences = new ArrayList<>();
		
		for(int index : this.getIndexes(compareableValue.charAt(indexCompareableValue), value)) {
			
			String strSeq = getEqualStrSequence(index, value, indexCompareableValue, compareableValue);
			
			if(strSeq.length() > 1)
				strSequences.add(strSeq);
			
		}
		
		return strSequences;
		
	}
	
	private List<Integer> getIndexes(char c, String value) {
		
		List<Integer> indexes = new ArrayList<>();
		
		int lastIndex = 0;
		
		do {
			
			lastIndex = value.indexOf(c, lastIndex);
			
			if(lastIndex != -1) indexes.add(lastIndex);
			
		} while(lastIndex != -1);
		
		return indexes;
		
	}
	
	private String getEqualStrSequence(int indexValue, String value, int indexCompareableValue, String compareableValue) {
		
		StringBuilder seq = new StringBuilder("");
		
		while(value.length() > indexValue && 
			  compareableValue.length() > indexCompareableValue &&
			  value.charAt(indexValue) == compareableValue.charAt(indexCompareableValue)) {
				
			seq.append(value.charAt(indexValue));
				
		}
		
		return seq.toString();
		
	}
	
	private void removeDuplicates(List<String> list) {
		
		list.removeIf(str -> {
			
			return list.indexOf(str) != list.lastIndexOf(str);
			
		});
	}

	@Override
	public <T> Similarity<T> getHighestSimilarity(String value, List<T> compareableList, Supplier<T> compareableValue,
			int maxAmount) {
		// TODO Auto-generated method stub
		return null;
	}
}
