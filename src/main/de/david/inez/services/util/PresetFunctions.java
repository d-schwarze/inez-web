package de.david.inez.services.util;

public class PresetFunctions {

	public static double getCenterFactor(int inputLength, int index) {
		
		return 1 - (Math.abs((double) (inputLength / 2.0 - index)) / inputLength);
		
	}
	
	public static double getStartFactor(int inputLength, int index) {
		
		return 1 - (Math.abs((double) (inputLength / 2.0 - index)) / inputLength);
		
	}
	
}
