package de.david.inez.services.util;

import de.david.inez.models.Numeral;

public class IndexedNumeral {

	private Numeral numeral;
	
	private int index;
	
	public IndexedNumeral(Numeral numeral, int index) {
		
		this.numeral = numeral;
		this.index = index;
		
	}

	public Numeral getNumeral() {
		return numeral;
	}

	public void setNumeral(Numeral numeral) {
		this.numeral = numeral;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
