package de.david.inez.services.util;

import java.util.List;
import java.util.function.Function;

public class ExternalRatingSet<T> {

	public List<T> dataSet;
	
	public Function<T, String> getCompareableValue;
	
	public ExternalRatingSet(List<T> dataSet, Function<T, String> getCompareableValue) {
		
		this.dataSet = dataSet;
		this.getCompareableValue = getCompareableValue;
		
	}	
}
