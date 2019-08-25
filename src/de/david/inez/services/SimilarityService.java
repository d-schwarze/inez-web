package de.david.inez.services;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import de.david.inez.services.util.Similarity;

public interface SimilarityService {

	public <T> List<Similarity<T>> getSimilarities(String value, List<T> compareableList, Function<T, String> getCompareableValue);
	
	public <T> List<Similarity<T>> getHighestSimilarites(String value, List<T> compareableList, Function<T, String> getCompareableValue, int maxAmount);
	
	public <T> Similarity<T> getHighestSimilarity(String value, List<T> compareableList, Function<T, String> getCompareableValue);
	
	public <T> List<Similarity<T>> getSortedSimilarities(String value, List<T> compareableList, Function<T, String> getCompareableValue);
	
	public <T> Similarity<T> getSimiliarty(String value, T compareableModel, Function<T, String> getCompareableValue);
	
	public boolean equals(String value, String compareableValue);
	
	public int getAmountOfEqualLetters(String value, String compareableValue);
	
	public String getLongestEqualStrSequence(String value, String compareableValue);
	
	public List<String> getEqualStrSequences(String value, String compareableValue);
	
	public double generateSimiliarityRating(String value, String compareableValue);
	
}
