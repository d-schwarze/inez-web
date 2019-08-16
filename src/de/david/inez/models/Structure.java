package de.david.inez.models;

import java.util.ArrayDeque;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Structure {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	private OrderedGroups orderedGroups;
	
	public Structure() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public OrderedGroups getOrderedGroups() {
		return orderedGroups;
	}

	public void setOrderedGroups(OrderedGroups orderedGroups) {
		this.orderedGroups = orderedGroups;
	}
}
