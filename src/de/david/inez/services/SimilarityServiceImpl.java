package de.david.inez.services;

import java.util.List;
import java.util.function.Supplier;

import de.david.inez.services.util.Similarity;

public class SimilarityServiceImpl implements SimilarityService {

	@Override
	public <T> List<Similarity<T>> getSimilarities(String value, List<T> compareableList,
			Supplier<T> compareableValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<Similarity<T>> getHighestSimilarites(String value, List<T> compareableList,
			Supplier<T> compareableValue, int maxAmount) {
		// TODO Auto-generated method stub
		return null;
	}

}
