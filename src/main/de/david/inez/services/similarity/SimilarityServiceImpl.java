package de.david.inez.services.similarity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.david.inez.services.util.SimilarityUtil;
import de.david.inez.services.util.rating.Rating;
import de.david.inez.services.util.rating.Similarity;

import static de.david.inez.services.util.SimilarityUtil.*;

@Service
public class SimilarityServiceImpl implements SimilarityService {

	@Autowired
	private ComplexSimilarityService complexSimilarityService;
	
	@Override
	public <T> List<Similarity<T>> getSimilarities(String input, List<T> compareableList,
			Function<T, String> getCompareableValue) {
		
		return complexSimilarityService.getSimilaritiesWithInputRating(input, compareableList, getCompareableValue, null);
		
	}

	@Override
	public <T> List<Similarity<T>> getSimilaritiesExtensive(String input, List<T> compareableList,
			Function<T, List<String>> getCompareableValues) {
		
		return complexSimilarityService.getSimilaritiesExtensiveWithInputRating(input, compareableList, getCompareableValues, null);
		
	}

	@Override
	public <T> List<Similarity<T>> getHighestSimilarites(String input, List<T> compareableList,
			Function<T, String> getCompareableValue, int maxAmount) {
		
		return complexSimilarityService.getHighestSimilaritesWithInputRating(input, compareableList, getCompareableValue, maxAmount, null);
		
	}

	@Override
	public <T> List<Similarity<T>> getHighestSimilaritesExtensive(String input, List<T> compareableList,
			Function<T, List<String>> getCompareableValues, int maxAmount) {
		
		return complexSimilarityService.getHighestSimilaritesExtensiveWithInputRating(input, compareableList, getCompareableValues, maxAmount, null);
		
	}

	@Override
	public <T> Similarity<T> getHighestSimilarity(String input, List<T> compareableList,
			Function<T, String> getCompareableValue) {
		
		return complexSimilarityService.getHighestSimilarityWithInputRating(input, compareableList, getCompareableValue, null);
		
	}

	@Override
	public <T> List<Similarity<T>> getSortedSimilarities(String input, List<T> compareableList,
			Function<T, String> getCompareableValue) {
		
		return complexSimilarityService.getSortedSimilaritiesWithInputRating(input, compareableList, getCompareableValue, null);
		
	}

	@Override
	public <T> List<Similarity<T>> getSortedSimilaritiesExtensive(String input, List<T> compareableList,
			Function<T, List<String>> getCompareableValues) {
		
		return complexSimilarityService.getSortedSimilaritiesExtensiveWithInputRating(input, compareableList, getCompareableValues, null);
		
	}

	@Override
	public <T> Similarity<T> getSimiliarty(String input, T compareableModel, Function<T, String> getCompareableValue) {
		
		return complexSimilarityService.getSimiliartyWithInputRating(input, compareableModel, getCompareableValue, null);
		
	}

	@Override
	public <T> Similarity<T> getSimiliartyExtensive(String input, T compareableModel,
			Function<T, List<String>> getCompareableValues) {
		
		return complexSimilarityService.getSimiliartyExtensiveWithInputRating(input, compareableModel, getCompareableValues, null);
		
	}

	@Override
	public <T> List<Similarity<T>> getSimiliaritiesWithMinRating(String input, List<T> compareableList,
			Function<T, String> getCompareableValue, double minRating) {
		
		return complexSimilarityService.getSimiliaritiesWithMinRatingAndInputRating(input, compareableList, getCompareableValue, minRating, null);
		
	}

	@Override
	public <T> List<Similarity<T>> getSimiliaritiesWithMinRatingExtensive(String input, List<T> compareableList,
			Function<T, List<String>> getCompareableValues, double minRating) {
		
		return complexSimilarityService.getSimiliaritiesWithMinRatingExtensiveAndInputRating(input, compareableList, getCompareableValues, minRating, null);
		
	}

}
