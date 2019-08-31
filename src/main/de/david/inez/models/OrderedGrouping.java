package de.david.inez.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class OrderedGrouping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<OrderedGroup> orderedGroups = new ArrayList<>();
	
	public OrderedGrouping() {
		
	}
	
	public void addOrderedGroup(OrderedGroup orderedGroup) {
		
		if(containsOrderedGroup(orderedGroup))
			return;
		
		if(isOrderNumberInUse(orderedGroup.getOrderNumber())) {
			
			this.insertOrderdGroup(orderedGroup);
			
		} else  {
			
			this.addOrderedGroupToLastPosition(orderedGroup);
			
		}	
	}
	
	public void addOrderedGroupToLastPosition(OrderedGroup orderedGroup) {
		
		int newOrderNumber = this.orderedGroups.size();
		
		orderedGroup.setOrderNumber(newOrderNumber);
		
		this.orderedGroups.add(orderedGroup);
		
	}
	
	private void insertOrderdGroup(OrderedGroup orderedGroup) {
		
		for(OrderedGroup og : orderedGroups) {
			
			if(og.getOrderNumber() >= orderedGroup.getOrderNumber()) {
				
				og.increaseOrderNumber();
				
			}
			
		}
		
		this.orderedGroups.add(orderedGroup);
		
	}
	
	public boolean containsOrderedGroup(OrderedGroup orderedGroup) {
		
		return orderedGroups.stream().anyMatch(og -> og.getId() == orderedGroup.getId());
		
	}
	
	public boolean isOrderNumberInUse(int orderNumber) {
		
		return orderedGroups.stream().anyMatch(og -> og.getOrderNumber() == orderNumber);
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<OrderedGroup> getOrderedGroups() {
		return orderedGroups;
	}

	public void setOrderedGroups(List<OrderedGroup> orderedGroups) {
		this.orderedGroups = orderedGroups;
	}	
}
