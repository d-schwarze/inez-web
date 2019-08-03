package de.david.inez.models;

import java.sql.Date;
import java.util.List;

public class ShoppingList {

	private long id;
	
	private Date date;
	
	private Subsidiary subsidiary;
	
	private List<Entry> entries;
	
	public ShoppingList() {
		
		
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Subsidiary getSubsidiary() {
		return subsidiary;
	}

	public void setSubsidiary(Subsidiary subsidiary) {
		this.subsidiary = subsidiary;
	}

	public List<Entry> getEntries() {
		return entries;
	}

	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}
}
