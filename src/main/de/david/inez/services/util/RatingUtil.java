package de.david.inez.services.util;

import java.util.List;
import java.util.stream.Collectors;

import de.david.inez.services.util.rating.ReadableRating;

public class RatingUtil {

	public static <T, R extends ReadableRating> void sort(List<R> ratings) {
		
		ratings.sort((R m1, R m2) -> {
			
			if(m1.getRating() < m2.getRating()) return 1;
			
			if(m1.getRating() > m2.getRating()) return -1;
			
			return 0;
			
		});
	}
	
	public static <T, R extends ReadableRating> List<R> filterByMinRating(List<R> ratings, double minRating) {
		
		return ratings.stream().filter(s -> s.getRating() >= minRating).collect(Collectors.toList());
		
	}
	
	public static <T, R extends ReadableRating> List<R> highest(List<R> ratings, int maxAmount) {
		
		if(ratings.size() == 0 || maxAmount == 0) return ratings;
		
		int toIndex = ratings.size() >= maxAmount ? maxAmount : ratings.size();
		
		return ratings.subList(0, toIndex);
		
	}
}
