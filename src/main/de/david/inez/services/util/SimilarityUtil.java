package de.david.inez.services.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SimilarityUtil {
	
	public static List<String> getPossibleStrSequences(int indexCompareableValue, String compareableValue, String value) {
		
		List<String> strSequences = new ArrayList<>();
		
		for(int index : getIndexes(compareableValue.charAt(indexCompareableValue), value)) {
			
			String strSeq = getEqualStrSequence(index, value, indexCompareableValue, compareableValue);
			
			if(strSeq.length() > 1)
				strSequences.add(strSeq);
			
		}
		
		return strSequences;
		
	}
	
	private static List<Integer> getIndexes(char c, String value) {
		
		List<Integer> indexes = new ArrayList<>();
		
		int lastIndex = value.indexOf(c);
		
		while(lastIndex != -1) {
			
			indexes.add(lastIndex);
			
			lastIndex = value.indexOf(c, lastIndex + 1);
			
		} 
		
		return indexes;
		
	}
	
	private static String getEqualStrSequence(int indexValue, String value, int indexCompareableValue, String compareableValue) {
		
		StringBuilder seq = new StringBuilder("");
		
		while(value.length() > indexValue && 
			  compareableValue.length() > indexCompareableValue &&
			  value.charAt(indexValue) == compareableValue.charAt(indexCompareableValue)) {
				
			seq.append(value.charAt(indexValue));
			
			indexValue++;
			indexCompareableValue++;
			
		}
		
		return seq.toString();
		
	}
	
	public static <T> void removeDuplicates(List<T> list) {
		
		list.removeIf(elem -> {
			
			return list.indexOf(elem) != list.lastIndexOf(elem);
			
		});
	}
	
	
	public static boolean equals(String value, String compareableValue) {

		return value.equals(compareableValue);
		
	}

	
	public static int getAmountOfEqualLetters(String value, String compareableValue) {
		
		int amountOfEqualLetters = 0;
		
		for(String s : compareableValue.split("")) {
			
			if(value.contains(s)) amountOfEqualLetters++;
			
		}
		
		return amountOfEqualLetters;
		
	}
	
	public static List<Integer> getIndexesOfEqualLetters(String value, String compareableValue) {
		
		List<Integer> indexes = new ArrayList<>();
		
		for(char s : compareableValue.toCharArray()) {
			
			indexes.addAll(getIndexes(s, value));
			
		}
		
		return indexes;
	}

	
	public static String getLongestEqualStrSequence(String value, String compareableValue) {
		
		List<String> strSequences = getEqualStrSequences(value, compareableValue);
		
		return strSequences.stream().max((String s1, String s2) -> {
			
			if(s1.length() > s2.length()) return 1;
			
			if(s1.length() == s2.length()) return 0;
			
			return -1;
			
		}).orElse("");
	}

	public static List<String> getEqualStrSequences(String value, String compareableValue) {
		
		List<String> strSequences = new ArrayList<>();
		
		for(int i = 0; i < compareableValue.length(); i++) {
			
			strSequences.addAll(getPossibleStrSequences(i, compareableValue, value));
			
		}
		
		removeDuplicates(strSequences);
		
		return strSequences;
		
	}
	
	public static <T> void sort(List<Similarity<T>> similarities) {
		
		similarities.sort((Similarity<T> m1, Similarity<T> m2) -> {
			
			if(m1.getRating() < m2.getRating()) return 1;
			
			if(m1.getRating() > m2.getRating()) return -1;
			
			return 0;
			
		});
	}
	
	public static <T> List<Similarity<T>> filterByMinRating(List<Similarity<T>> similarities, double minRating) {
		
		return similarities.stream().filter(s -> s.getRating() >= minRating).collect(Collectors.toList());
		
	}
	
	public static double getSum(double[] list, int fromIndex, int toIndex) {
		
		double sum = 0.0;
		
		for(int i = fromIndex; i < toIndex; i++) sum += list[i];
		
		return sum;
		
	}
	
	public static <T> List<Similarity<T>> highest(List<Similarity<T>> similarities, int maxAmount) {
		
		if(similarities.size() == 0 || maxAmount == 0) return similarities;
		
		int toIndex = similarities.size() >= maxAmount ? maxAmount : similarities.size();
		
		return similarities.subList(0, toIndex);
		
	}
	
}
