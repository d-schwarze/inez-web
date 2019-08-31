package de.david.inez.services.similarity;

import java.util.List;
import java.util.function.Function;

import de.david.inez.services.util.rating.Similarity;

public interface SimilarityService {

	public <T> List<Similarity<T>> getSimilarities(String input, List<T> compareableList, Function<T, String> getCompareableValue);
	
	public <T> List<Similarity<T>> getSimilaritiesExtensive(String input, List<T> compareableList, Function<T, List<String>> getCompareableValues);
	
	public <T> List<Similarity<T>> getHighestSimilarites(String input, List<T> compareableList, Function<T, String> getCompareableValue, int maxAmount);
	
	public <T> List<Similarity<T>> getHighestSimilaritesExtensive(String input, List<T> compareableList, Function<T, List<String>> getCompareableValues, int maxAmount);
	
	public <T> Similarity<T> getHighestSimilarity(String input, List<T> compareableList, Function<T, String> getCompareableValue);
	
	public <T> List<Similarity<T>> getSortedSimilarities(String input, List<T> compareableList, Function<T, String> getCompareableValue);
	
	public <T> List<Similarity<T>> getSortedSimilaritiesExtensive(String input, List<T> compareableList, Function<T, List<String>> getCompareableValues);
	
	public <T> Similarity<T> getSimiliarty(String input, T compareableModel, Function<T, String> getCompareableValue);
	
	public <T> Similarity<T> getSimiliartyExtensive(String input, T compareableModel, Function<T, List<String>> getCompareableValues);
	
	public <T> List<Similarity<T>> getSimiliaritiesWithMinRating(String input, List<T> compareableList, Function<T, String> getCompareableValue, double minRating);
	
	public <T> List<Similarity<T>> getSimiliaritiesWithMinRatingExtensive(String input, List<T> compareableList, Function<T, List<String>> getCompareableValues, double minRating);
	
}
