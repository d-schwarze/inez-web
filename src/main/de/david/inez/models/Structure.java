package de.david.inez.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Structure {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	private OrderedGrouping orderedGrouping;
	
	public Structure() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public OrderedGrouping getOrderedGrouping() {
		return orderedGrouping;
	}

	public void setOrderedGrouping(OrderedGrouping orderedGrouping) {
		this.orderedGrouping = orderedGrouping;
	}
}
