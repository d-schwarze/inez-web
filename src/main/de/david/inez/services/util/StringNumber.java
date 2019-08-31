package de.david.inez.services.util;

public class StringNumber {

	private String value;
	
	private int index;
	
	public StringNumber(String value, int index) {
		
		this.value = value;
		this.index = index;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
