package de.david.inez.services.util;

import java.util.ArrayList;
import java.util.List;

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
		
		List<String> strSequences = getEqualStrSequences(value, compareableValue, true);
		
		return strSequences.stream().max((String s1, String s2) -> {
			
			if(s1.length() > s2.length()) return 1;
			
			if(s1.length() == s2.length()) return 0;
			
			return -1;
			
		}).orElse("");
	}

	public static List<String> getEqualStrSequences(String value, String compareableValue, boolean withWhitespaces) {
		
		List<String> strSequences = new ArrayList<>();
		
		for(int i = 0; i < compareableValue.length(); i++) {
			
			strSequences.addAll(getPossibleStrSequences(i, compareableValue, value));
			
		}
		
		if(!withWhitespaces)
			removeWhitespaces(strSequences);
		
		removeDuplicates(strSequences);
		//Possibibily: Compareablevalue: Milch, Value: 1l Milch -> equal seq: Milch, ilch, lch, ch -> too much -> remove ilch, lch, ch
		//Something like: Compareablevalue: Milch, value: Mi2ch -> equal seq: Mi, ch should only be possible
		removeInnerSequences(strSequences);
		
		return strSequences;
		
	}
	
	public static void removeInnerSequences(List<String> sequences) {
		
		sequences.removeIf(seq -> sequencesContain(seq, sequences));
		
	}
	
	public static boolean sequencesContain(String sequence, List<String> sequences) {
		
		for(String s : sequences) {
			
			if(!s.equals(sequence) && s.contains(sequence)) return true;
			
		}
		
		return false;
		
	}
	
	public static void removeWhitespaces(List<String> sequences) {
		
		for(String seq : sequences) {
			
			seq.replace(" ", "");
			
		}
		
		for(int i = 0; i < sequences.size(); i++) {
			String seq = sequences.get(i);
			
			String newSeq = seq.replace(" ", "");
			
			sequences.set(i, newSeq);
			
			
		}
		
	}
	
	public static double getSum(double[] list, int fromIndex, int length) {
		
		double sum = 0.0;
		
		for(int i = fromIndex; i < (fromIndex + length); i++) sum += list[i];
		
		return sum;
		
	}
	
	public static String containsAny(String value, List<String> compareableValues) {
		
		for(String s : compareableValues) {
			
			if(value.toLowerCase().contains(s.toLowerCase() + " ")) return s;
			if(value.toLowerCase().contains(" " + s.toLowerCase())) return s;
			
		}
		
		return null;
		
	}
	
}
