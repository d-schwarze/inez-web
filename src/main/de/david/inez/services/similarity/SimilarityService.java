package de.david.inez.services.similarity;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import de.david.inez.services.util.Similarity;

public interface SimilarityService {

	public <T> List<Similarity<T>> getSimilarities(String value, List<T> compareableList, Function<T, String> getCompareableValue);
	
	public <T> List<Similarity<T>> getSimilaritiesExtensive(String value, List<T> compareableList, Function<T, List<String>> getCompareableValues);
	
	public <T> List<Similarity<T>> getHighestSimilarites(String value, List<T> compareableList, Function<T, String> getCompareableValue, int maxAmount);
	
	public <T> List<Similarity<T>> getHighestSimilaritesExtensive(String value, List<T> compareableList, Function<T, List<String>> getCompareableValues, int maxAmount);
	
	public <T> Similarity<T> getHighestSimilarity(String value, List<T> compareableList, Function<T, String> getCompareableValue);
	
	public <T> List<Similarity<T>> getSortedSimilarities(String value, List<T> compareableList, Function<T, String> getCompareableValue);
	
	public <T> List<Similarity<T>> getSortedSimilaritiesExtensive(String value, List<T> compareableList, Function<T, List<String>> getCompareableValues);
	
	public <T> Similarity<T> getSimiliarty(String value, T compareableModel, Function<T, String> getCompareableValue);
	
	public <T> Similarity<T> getSimiliartyExtensive(String value, T compareableModel, Function<T, List<String>> getCompareableValues);
	
	public <T> List<Similarity<T>> getSimiliaritiesWithMinRating(String value, List<T> compareableList, Function<T, String> getCompareableValue, double minRating);
	
	public <T> List<Similarity<T>> getSimiliaritiesWithMinRatingExtensive(String value, List<T> compareableList, Function<T, List<String>> getCompareableValues, double minRating);
	
	public double generateSimiliarityRating(String value, String compareableValue);
	
	public double generateStrSequencesRating(String value, List<String> strSequences);
	
}
