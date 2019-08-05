package de.david.inez.models;

import java.util.ArrayDeque;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Structure {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToMany(cascade = CascadeType.ALL)
	private ArrayDeque<Group> orderedGroupsRoute = new ArrayDeque<Group>();
	
	public Structure() {
		
	}

	public ArrayDeque<Group> getOrderedGroupsRoute() {
		return orderedGroupsRoute;
	}

	public void setOrderedGroupsRoute(ArrayDeque<Group> orderedGroupsRoute) {
		this.orderedGroupsRoute = orderedGroupsRoute;
	}

}
