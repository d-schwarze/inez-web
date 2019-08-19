package de.david.inez.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class OrderedGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private int orderNumber;
	
	@OneToOne
	private ProductGroup productGroup;

	public OrderedGroup() { }

	public void increaseOrderNumber() {
		
		this.orderNumber++;
		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public ProductGroup getGroup() {
		return productGroup;
	}

	public void setGroup(ProductGroup productGroup) {
		this.productGroup = productGroup;
	}	
}
