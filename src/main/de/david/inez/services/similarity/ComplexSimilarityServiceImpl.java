package de.david.inez.services.similarity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import de.david.inez.services.util.SimilarityUtil;
import de.david.inez.services.util.rating.Rating;
import de.david.inez.services.util.rating.Similarity;

import static de.david.inez.services.util.SimilarityUtil.*;
import static de.david.inez.services.util.RatingUtil.*;

@Service
public class ComplexSimilarityServiceImpl implements ComplexSimilarityService {

	
	@Override
	public <T> List<Similarity<T>> getHighestSimilaritesWithInputRating(String value, List<T> compareableList,
			Function<T, String> getCompareableValue, int maxAmount, List<String> externalData) {
		
		List<Similarity<T>> sortedSimilarities = this.getSortedSimilaritiesWithInputRating(value, compareableList, getCompareableValue, externalData);
		
		return highest(sortedSimilarities, maxAmount);
	}

	@Override
	public <T> List<Similarity<T>> getHighestSimilaritesExtensiveWithInputRating(String value, List<T> compareableList,
			Function<T, List<String>> getCompareableValues, int maxAmount,
			List<String> externalData) {
		
		List<Similarity<T>> sortedSimilarities = this.getSortedSimilaritiesExtensiveWithInputRating(value, compareableList, getCompareableValues, externalData);
		
		return highest(sortedSimilarities, maxAmount);
	}

	@Override
	public <T> List<Similarity<T>> getSimilaritiesWithInputRating(String value, List<T> compareableList,
			Function<T, String> getCompareableValue, List<String> externalData) {
		
		List<Similarity<T>> similarities = new ArrayList<>();
		
		for(T model : compareableList) {
			
			Similarity<T> similarity = this.getSimiliartyWithInputRating(value, model, getCompareableValue, externalData);
			
			similarities.add(similarity);
			
		}
		
		return similarities;
	}

	@Override
	public <T> List<Similarity<T>> getSimilaritiesExtensiveWithInputRating(String value, List<T> compareableList,
			Function<T, List<String>> getCompareableValues, List<String> externalData) {
		
		List<Similarity<T>> similarities = new ArrayList<>();
		
		for(T model : compareableList) {
			
			Similarity<T> similarity = this.getSimiliartyExtensiveWithInputRating(value, model, getCompareableValues, externalData);
			
			similarities.add(similarity);
			
		}
		
		return similarities;
	}

	@Override
	public <T> List<Similarity<T>> getSortedSimilaritiesWithInputRating(String value, List<T> compareableList,
			Function<T, String> getCompareableValue, List<String> externalData) {
		
		List<Similarity<T>> similarities = this.getSimilaritiesWithInputRating(value, compareableList, getCompareableValue, externalData);
		
		sort(similarities);
		
		return similarities;
	}

	@Override
	public <T> List<Similarity<T>> getSortedSimilaritiesExtensiveWithInputRating(String value, List<T> compareableList,
			Function<T, List<String>> getCompareableValues, List<String> externalData) {
		
		List<Similarity<T>> similarities = this.getSimilaritiesExtensiveWithInputRating(value, compareableList, getCompareableValues, externalData);
		
		sort(similarities);
		
		return similarities;
	}
	
	@Override
	public <T> Similarity<T> getSimiliartyWithInputRating(String value, T compareableModel,
			Function<T, String> getCompareableValue, List<String> externalData) {
		
		Similarity<T> similarity = new Similarity<>();
		
		similarity.setModel(compareableModel);
		
		similarity.setRating(this.generateSimiliarityRatingWithInputRating(value, getCompareableValue.apply(compareableModel), externalData));
		
		return similarity;
	}
	
	@Override
	public <T> Similarity<T> getSimiliartyExtensiveWithInputRating(String input, T compareableModel,
			Function<T, List<String>> getCompareableValues, List<String> externalData) {
		
		Similarity<T> similarity = new Similarity<>();
		
		similarity.setModel(compareableModel);
		
		
		double rating = getCompareableValues.apply(compareableModel)
											.stream()
											.mapToDouble(compareableValue -> this.generateSimiliarityRatingWithInputRating(input, compareableValue, externalData))
											.max().orElse(0.0);
		
		similarity.setRating(rating);
		
		return similarity;
	}
	
	@Override
	public <T> Similarity<T> getHighestSimilarityWithInputRating(String input, List<T> compareableList,
			Function<T, String> getCompareableValue, List<String> externalData) {
		
		List<Similarity<T>> sortedSimilarities = this.getSortedSimilaritiesWithInputRating(input, compareableList, getCompareableValue, externalData);
		
		if(sortedSimilarities.size() != 0)
			return sortedSimilarities.get(0);
		
		throw new IndexOutOfBoundsException("0 similarities were found. Getting the hightest similarity is not possible.");
	}

	@Override
	public <T> List<Similarity<T>> getSimiliaritiesWithMinRatingAndInputRating(String input, List<T> compareableList,
			Function<T, String> getCompareableValue, double minRating, List<String> externalData) {
		
		List<Similarity<T>> similarities = this.getSimilaritiesWithInputRating(input, compareableList, getCompareableValue, externalData);
		
		return filterByMinRating(similarities, minRating);
	}

	@Override
	public <T> List<Similarity<T>> getSimiliaritiesWithMinRatingExtensiveAndInputRating(String input,
			List<T> compareableList, Function<T, List<String>> getCompareableValues, double minRating,
			List<String> externalData) {
		
		List<Similarity<T>> similarities = this.getSimilaritiesExtensiveWithInputRating(input, compareableList, getCompareableValues, externalData);
		
		return filterByMinRating(similarities, minRating);
		
	}
	
	@Override
	public double generateSimiliarityRatingWithInputRating(String input, String compareableValue, List<String> externalData) {
		input = input.toLowerCase();
		compareableValue = compareableValue.toLowerCase();
		
		if(SimilarityUtil.equals(input, compareableValue)) return 100.00;
		
		double[] inputRating = getInputRating(input, externalData);
		
		double rating = 0.0;
		
		rating += getRatingForAmountOfEqualLetters(20.0, input, compareableValue, inputRating); 
		
		rating += getRatingForEqualStrSequences(35.0, input, compareableValue, inputRating);
		
		rating += getRatingForLongestEqualStrSequence(45.0, input, compareableValue, inputRating);
		
		rating *= getRatingForLengthComparison(input, compareableValue);
		
		return rating;
	}
	
	private double getRatingForLengthComparison(String input, String compareableValue) {
		
		double rating;
		
		if(input.length() < compareableValue.length())
			rating = (((double) input.length() / compareableValue.length()) + ((1.0 - (double) input.length() / compareableValue.length()) * Math.exp((double) (compareableValue.length() - input.length()) * -0.7)));
		else
			rating = (((double) compareableValue.length() / input.length()) + ((1.0 - (double) compareableValue.length() / input.length()) * Math.exp((double) (input.length() - compareableValue.length()) * -0.7)));
		
		return rating;
	}
	
	private double getRatingForAmountOfEqualLetters(double ratingShare, String input, String compareableValue, double[] inputRating) {
		
		List<Integer> equalLetterIndexes = getIndexesOfEqualLetters(input, compareableValue);
		removeDuplicates(equalLetterIndexes);
		
		double rating = 0.0;
		
		for(int index : equalLetterIndexes) {
			
			rating += inputRating[index];
			
		}
		
		return (rating / input.length()) * ratingShare;
		
	}
	
	private double getRatingForEqualStrSequences(double ratingShare, String input, String compareableValue, double[] inputRating) {
		
		List<String> equalStrSequences = getEqualStrSequences(input, compareableValue, false);
		
		double sequencesRating = 1.0;
		
		for(String sequence : equalStrSequences) {
			
			sequencesRating *= getRatingForEqualStrSequence(input, sequence, inputRating);
			
		}
		
		double rating = ((double) sequencesRating / input.length()) * ratingShare;
		
		return rating;
		
	}
	
	private double getRatingForEqualStrSequence(String input, String sequence, double[] inputRating) {
		
		double rating = 0.0;
		
		int lastIndex = input.indexOf(sequence);
		
		while(lastIndex != -1) {
			
			rating += getSum(inputRating, lastIndex, sequence.length());
			
			lastIndex = input.indexOf(sequence, lastIndex + 1);
			
		} 
		
		return rating;
	}
	
	private double getRatingForLongestEqualStrSequence(double ratingShare, String input, String compareableValue, double[] inputRating) {
		
		String longestSeq = getLongestEqualStrSequence(input, compareableValue);
		
		int index = input.indexOf(longestSeq);
		
		double seqRating = getSum(inputRating, index, longestSeq.length());
		
		double rating = (seqRating / input.length()) * ratingShare;
		
		return rating;
		
		
	}
	
	
	public double[] getInputRating(String input, List<String> externalData) {
		
		double[] inputRating = new double[input.length()];
		Arrays.fill(inputRating, 1.0);
		
		generateInputRating(inputRating, input, externalData);
		
		return inputRating;
		
	}
	

	private void generateInputRating(double[] inputRating, String input, List<String> externalData) {
		
		if(externalData == null) return;
		
		for(String value : externalData) {
			
			adjustInputRating(inputRating, input, value);
			
		}
	}

	private void adjustInputRating(double[] inputRating, String input, String compareableValue) {
		
		adjustInputRatingByEquality(inputRating, 0.2, input, compareableValue);
		
		adjustInputRatingByEqualLetters(inputRating, 0.98, input, compareableValue);
		
		adjustInputRatingByStrSequences(inputRating, 0.5, input, compareableValue);
		
		adjustInputRatingByLongestSequence(inputRating, 0.4, input, compareableValue);
		
	}
	
	private void adjustInputRatingByLongestSequence(double[] inputRating, double factor, String input, String compareableValue) {
		
		String longestEqualSeq = getLongestEqualStrSequence(input, compareableValue);
		
		adjustInputRatingByStrSequence(inputRating, factor, input, longestEqualSeq);
		
	}

	private void adjustInputRatingByStrSequences(double[] inputRating, double factor, String input, String compareableValue) {
		
		List<String> equalSequences = getEqualStrSequences(input, compareableValue, false);
		
		for(String equalSeq : equalSequences) {
			
			adjustInputRatingByStrSequence(inputRating, factor, input, equalSeq);
			
		}
	}
	
	private void adjustInputRatingByStrSequence(double[] inputRating, double factor, String input, String sequence) {
		
		int lastIndex = input.indexOf(sequence);
		
		while(lastIndex != -1 && lastIndex < input.length() && !sequence.equals("")) {
			
			adjustInputRatingByFactorAndIndexes(inputRating, factor, lastIndex, lastIndex + sequence.length());
			
			lastIndex = input.indexOf(sequence, lastIndex + 1);
			
		} 
	}

	private void adjustInputRatingByEqualLetters(double[] inputRating, double factor, String input, String compareableValue) {
		
		List<Integer> indexes = getIndexesOfEqualLetters(input, compareableValue);
		
		adjustInputRatingByFactorAndIndexes(inputRating, factor, indexes);
		
	}

	private void adjustInputRatingByEquality(double[] inputRating, double factor, String input, String compareableValue) {
		
		if(SimilarityUtil.equals(input, compareableValue)) adjustCompleteInputRatingByFactor(inputRating, factor);
		
	}
	
	private void adjustCompleteInputRatingByFactor(double[] inputRating, double factor) {
		
		for(var i = 0; i < inputRating.length; i++) inputRating[i] = inputRating[i] * factor;
		
	}
	
	private void adjustInputRatingByFactorAndIndexes(double[] inputRating, double factor, List<Integer> indexes) {
		
		for(int index : indexes) inputRating[index] = inputRating[index] * factor;
		
	}
	
	private void adjustInputRatingByFactorAndIndexes(double[] inputRating, double factor, int fromIndex, int toIndex) {
		
		for(int i = fromIndex; i < toIndex; i++) inputRating[i] = inputRating[i] * factor;
		
	}
}
