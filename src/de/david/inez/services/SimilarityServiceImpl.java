package de.david.inez.services;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import de.david.inez.services.util.Similarity;

public class SimilarityServiceImpl implements SimilarityService {

	@Override
	public <T> List<Similarity<T>> getSimilarities(String value, List<T> compareableList,
			Function<T, String> getCompareableValue) {
		
		List<Similarity<T>> similarities = new ArrayList<>();
		
		for(T model : compareableList) {
			
			Similarity<T> similarity = this.getSimiliarty(value, model, getCompareableValue);
			
			similarities.add(similarity);
			
		}
		
		return similarities;
	}
	
	@Override
	public <T> List<Similarity<T>> getSimilaritiesExtensive(String value, List<T> compareableList,
			Function<T, List<String>> getCompareableValues) {
		
		List<Similarity<T>> similarities = new ArrayList<>();
		
		for(T model : compareableList) {
			
			Similarity<T> similarity = this.getSimiliartyExtensive(value, model, getCompareableValues);
			
			similarities.add(similarity);
			
		}
		
		return similarities;
		
	}
	
	@Override
	public <T> List<Similarity<T>> getSimiliaritiesWithMinRating(String value, List<T> compareableList,
			Function<T, String> getCompareableValue, double minRating) {
		
		List<Similarity<T>> similarities = this.getSimilarities(value, compareableList, getCompareableValue);
		
		return similarities.stream().filter(s -> s.getRating() >= minRating).collect(Collectors.toList());
		
	}
	
	@Override
	public <T> List<Similarity<T>> getSimiliaritiesWithMinRatingExtensive(String value, List<T> compareableList,
			Function<T, List<String>> getCompareableValues, double minRating) {
		
		List<Similarity<T>> similarities = this.getSimilaritiesExtensive(value, compareableList, getCompareableValues);
		
		return similarities.stream().filter(s -> s.getRating() >= minRating).collect(Collectors.toList());
	}
	
	@Override
	public <T> List<Similarity<T>> getSortedSimilarities(String value, List<T> compareableList,
			Function<T, String> getCompareableValue) {
		
		List<Similarity<T>> similarities = this.getSimilarities(value, compareableList, getCompareableValue);
		
		similarities.sort((Similarity<T> m1, Similarity<T> m2) -> {
			
			if(m1.getRating() > m2.getRating()) return 1;
			
			if(m1.getRating() == m2.getRating()) return 0;
			
			return -1;
			
		});
		
		return similarities;
	}

	@Override
	public <T> List<Similarity<T>> getHighestSimilarites(String value, List<T> compareableList,
			Function<T, String> getCompareableValue, int maxAmount) {
		
		List<Similarity<T>> sortedSimilarities = this.getSortedSimilarities(value, compareableList, getCompareableValue);
		
		if(sortedSimilarities.size() == 0 || maxAmount == 0) return sortedSimilarities;
		
		int toIndex = sortedSimilarities.size() >= maxAmount ? (maxAmount - 1) : (sortedSimilarities.size() - 1);
		
		return sortedSimilarities.subList(0, toIndex);
		
	}
	
	@Override
	public <T> Similarity<T> getHighestSimilarity(String value, List<T> compareableList,
			Function<T, String> getCompareableValue) {
		
		List<Similarity<T>> sortedSimilarities = this.getSortedSimilarities(value, compareableList, getCompareableValue);
		
		if(sortedSimilarities.size() != 0)
			return sortedSimilarities.get(0);
		
		throw new IndexOutOfBoundsException("0 similarities were found. Getting the hightest similarity is not possible.");
		
	}

	@Override
	public <T> Similarity<T> getSimiliarty(String value, T compareableModel, Function<T, String> getCompareableValue) {
		
		Similarity<T> similarity = new Similarity<>();
		
		similarity.setModel(compareableModel);
		
		similarity.setRating(this.generateSimiliarityRating(value, getCompareableValue.apply(compareableModel)));
		
		return similarity;
		
	}
	
	@Override
	public <T> Similarity<T> getSimiliartyExtensive(String value, T compareableModel,
			Function<T, List<String>> getCompareableValues) {
		
		Similarity<T> similarity = new Similarity<>();
		
		similarity.setModel(compareableModel);
		
		double rating = getCompareableValues.apply(compareableModel)
											.stream()
											.mapToDouble(compareableValue -> this.generateSimiliarityRating(value, compareableValue))
											.max().orElse(0.0);
						
		similarity.setRating(rating);
		
		return similarity;
	}
	
	@Override
	public double generateSimiliarityRating(String value, String compareableValue) {
		
		if(this.equals(value, compareableValue)) return 100.00;
		
		double rating = 0.0;
		
		rating += 30 * (this.getAmountOfEqualLetters(value, compareableValue) / value.length()); 
		
		rating += 60 * (this.generateStrSequencesRating(value, this.getEqualStrSequences(value, compareableValue)));
		
		rating += 10 * (this.getLongestEqualStrSequence(value, compareableValue).length() / value.length());
		
		return rating;
	}
	
	private double generateStrSequencesRating(String value, List<String> strSequences) {
		
		if(strSequences.size() == 0) return 0.0;
		
		int strSequencesSizeMultiplication = strSequences.stream().mapToInt(s -> s.length()).reduce(1, Math::multiplyExact);
		
		double rating = 1 - (strSequences.size() / strSequencesSizeMultiplication);
		
		return rating;
		
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
		
		return strSequences.stream().max((String s1, String s2) -> {
			
			if(s1.length() > s2.length()) return 1;
			
			if(s1.length() == s2.length()) return 0;
			
			return -1;
			
		}).orElse("");
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
}
