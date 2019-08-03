package de.david.inez.models;

import java.util.LinkedList;
import java.util.Queue;

public class Structure {
	
	private Queue<Group> orderedGroupsRoute = new LinkedList<Group>();
	
	public Structure() {
		
	}

	public Queue<Group> getOrderedGroupsRoute() {
		return orderedGroupsRoute;
	}

	public void setOrderedGroupsRoute(Queue<Group> orderedGroupsRoute) {
		this.orderedGroupsRoute = orderedGroupsRoute;
	}

}
