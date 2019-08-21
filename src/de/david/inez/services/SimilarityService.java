package de.david.inez.services;

import java.util.List;
import java.util.function.Supplier;

import de.david.inez.services.util.Similarity;

public interface SimilarityService {

	public <T> List<Similarity<T>> getSimilarities(String value, List<T> compareableList, Supplier<T> compareableValue);
	
	public <T> List<Similarity<T>> getHighestSimilarites(String value, List<T> compareableList, Supplier<T> compareableValue, int maxAmount);
	
}
