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
public class OrderedGroups {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<OrderedGroup> orderedGroupsList = new ArrayList<>();
	
	public OrderedGroups() {
		
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
		
		int newOrderNumber = this.orderedGroupsList.size();
		
		orderedGroup.setOrderNumber(newOrderNumber);
		
		this.orderedGroupsList.add(orderedGroup);
		
	}
	
	private void insertOrderdGroup(OrderedGroup orderedGroup) {
		
		for(OrderedGroup og : orderedGroupsList) {
			
			if(og.getOrderNumber() >= orderedGroup.getOrderNumber()) {
				
				og.increaseOrderNumber();
				
			}
			
		}
		
		this.orderedGroupsList.add(orderedGroup);
		
	}
	
	public boolean containsOrderedGroup(OrderedGroup orderedGroup) {
		
		return orderedGroupsList.stream().anyMatch(og -> og.getId() == orderedGroup.getId());
		
	}
	
	public boolean isOrderNumberInUse(int orderNumber) {
		
		return orderedGroupsList.stream().anyMatch(og -> og.getOrderNumber() == orderNumber);
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<OrderedGroup> getOrderedGroups() {
		return orderedGroupsList;
	}

	public void setOrderedGroups(List<OrderedGroup> orderedGroups) {
		this.orderedGroupsList = orderedGroups;
	}	
}
