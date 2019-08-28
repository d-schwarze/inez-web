package de.david.inez.services.similarity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import de.david.inez.services.util.Similarity;
import de.david.inez.services.util.SimilarityUtil;

import static de.david.inez.services.util.SimilarityUtil.*;

@Service
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
		
		return filterByMinRating(similarities, minRating);
		
	}
	
	@Override
	public <T> List<Similarity<T>> getSimiliaritiesWithMinRatingExtensive(String value, List<T> compareableList,
			Function<T, List<String>> getCompareableValues, double minRating) {
		
		List<Similarity<T>> similarities = this.getSimilaritiesExtensive(value, compareableList, getCompareableValues);
		
		return filterByMinRating(similarities, minRating);
	}
	
	@Override
	public <T> List<Similarity<T>> getSortedSimilarities(String value, List<T> compareableList,
			Function<T, String> getCompareableValue) {
		
		List<Similarity<T>> similarities = this.getSimilarities(value, compareableList, getCompareableValue);
		
		sort(similarities);
		
		return similarities;
	}
	
	@Override
	public <T> List<Similarity<T>> getSortedSimilaritiesExtensive(String value, List<T> compareableList,
			Function<T, List<String>> getCompareableValues) {
		
		List<Similarity<T>> similarities = this.getSimilaritiesExtensive(value, compareableList, getCompareableValues);
		
		sort(similarities);
		
		return similarities;
		
	}

	@Override
	public <T> List<Similarity<T>> getHighestSimilarites(String value, List<T> compareableList,
			Function<T, String> getCompareableValue, int maxAmount) {
		
		List<Similarity<T>> sortedSimilarities = this.getSortedSimilarities(value, compareableList, getCompareableValue);
		
		return highest(sortedSimilarities, maxAmount);
		
	}
	
	@Override
	public <T> List<Similarity<T>> getHighestSimilaritesExtensive(String value, List<T> compareableList,
			Function<T, List<String>> getCompareableValues, int maxAmount) {
		
		List<Similarity<T>> sortedSimilarities = this.getSortedSimilaritiesExtensive(value, compareableList, getCompareableValues);
		
		return highest(sortedSimilarities, maxAmount);
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
	
	//Werte könnte man perfekt mit ML bekommen, nicht?
	@Override
	public double generateSimiliarityRating(String value, String compareableValue) {
		
		if(SimilarityUtil.equals(value, compareableValue)) return 100.00;
		
		double rating = 0.0;
		
		rating += 20.0 * ((double) getAmountOfEqualLetters(value, compareableValue) / (double) value.length()); 
		
		rating += 35.0 * ((double) this.generateStrSequencesRating(value, getEqualStrSequences(value, compareableValue)));
		
		rating += 45.0 * ((double) getLongestEqualStrSequence(value, compareableValue).length() / (double) value.length());
		
		if(value.length() < compareableValue.length())
			rating *= (((double) value.length() / (double) compareableValue.length()) + ((1.0 - (double) value.length() / (double) compareableValue.length()) * Math.exp((double) (compareableValue.length() - value.length()) * -0.7)));
		else
			rating *= (((double) compareableValue.length() / (double) value.length()) + ((1.0 - (double) compareableValue.length() / (double) value.length()) * Math.exp((double) (value.length() - compareableValue.length()) * -0.7)));
		
		return rating;
	}
	
	public double generateStrSequencesRating(String value, List<String> strSequences) {
		
		if(strSequences.size() == 0) return 0.0;
		
		double strSequencesSizeMultiplication = strSequences.stream().mapToInt(s -> s.length()).reduce(1, Math::multiplyExact);
		
		double rating = 1 - (strSequences.size() / strSequencesSizeMultiplication);
		
		return rating;
		
	}

	
}
