package de.david.inez.services;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import de.david.inez.services.util.Similarity;

public class SimilarityServiceImpl implements SimilarityService {

	@Override
	public <T> List<Similarity<T>> getSimilarities(String value, List<T> compareableList,
			Function<T, String> getCompareableValue) {
		
		for(T model : compareableList) {
			
			String compareableValue = getCompareableValue.apply(model);
			
		}
		
		return null;
	}

	@Override
	public <T> List<Similarity<T>> getHighestSimilarites(String value, List<T> compareableList,
			Supplier<T> compareableValue, int maxAmount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Similarity<T> getSimiliarty(String value, String compareableValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(String value, String compareableValue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getAmountOfEqualLetters(String value, String compareableValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CharSequence getLongestEqualCharSequence(String value, String compareableValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CharSequence> getEqualCharSequences(String value, String compareableValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Similarity<T> getHighestSimilarity(String value, List<T> compareableList, Supplier<T> compareableValue,
			int maxAmount) {
		// TODO Auto-generated method stub
		return null;
	}
}
