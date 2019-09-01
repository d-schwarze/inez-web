package de.david.inez.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import de.david.inez.controller.util.CustomJsonDateDeserializer;

@Entity
public class ShoppingList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String userId;
	
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public LocalDate date;
	
	@OneToOne
	@JoinColumn(name = "SUBSIDIARY_ID")
	private Branch branch;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Entry> entries;
	
	private boolean pinned = false;
	
	private String name;
	
	public ShoppingList() { }
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public List<Entry> getEntries() {
		return entries;
	}

	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isPinned() {
		return pinned;
	}

	public void setPinned(boolean pinned) {
		this.pinned = pinned;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
}
