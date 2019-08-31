package de.david.inez.services.similarity;

import java.util.List;
import java.util.function.Function;

import de.david.inez.services.util.rating.Similarity;

public interface ComplexSimilarityService {

	public <T> List<Similarity<T>> getHighestSimilaritesWithInputRating(String input, List<T> compareableList, Function<T, String> getCompareableValue, int maxAmount, List<String> externalData);
	
	public <T> List<Similarity<T>> getHighestSimilaritesExtensiveWithInputRating(String input, List<T> compareableList, Function<T, List<String>> getCompareableValues, int maxAmount, List<String> externalData);
	
	public <T> List<Similarity<T>> getSimilaritiesWithInputRating(String input, List<T> compareableList, Function<T, String> getCompareableValue, List<String> externalData);
	
	public <T> List<Similarity<T>> getSimilaritiesExtensiveWithInputRating(String input, List<T> compareableList, Function<T, List<String>> getCompareableValues, List<String> externalData);
	
	public <T> List<Similarity<T>> getSortedSimilaritiesWithInputRating(String input, List<T> compareableList, Function<T, String> getCompareableValue, List<String> externalData);
	
	public <T> List<Similarity<T>> getSortedSimilaritiesExtensiveWithInputRating(String input, List<T> compareableList, Function<T, List<String>> getCompareableValues, List<String> externalData);

	public <T> Similarity<T> getSimiliartyWithInputRating(String input, T compareableModel, Function<T, String> getCompareableValue, List<String> externalData);
	
	public <T> Similarity<T> getSimiliartyExtensiveWithInputRating(String input, T compareableModel, Function<T, List<String>> getCompareableValues, List<String> externalData);
	
	public <T> Similarity<T> getHighestSimilarityWithInputRating(String input, List<T> compareableList, Function<T, String> getCompareableValue, List<String> externalData);
	
	public <T> List<Similarity<T>> getSimiliaritiesWithMinRatingAndInputRating(String input, List<T> compareableList, Function<T, String> getCompareableValue, double minRating, List<String> externalData);
	
	public <T> List<Similarity<T>> getSimiliaritiesWithMinRatingExtensiveAndInputRating(String input, List<T> compareableList, Function<T, List<String>> getCompareableValues, double minRating, List<String> externalData);
	
	public double generateSimiliarityRatingWithInputRating(String input, String compareableValue, List<String> externalData);
	
	public double[] getInputRating(String input, List<String> externalData);
	
}
