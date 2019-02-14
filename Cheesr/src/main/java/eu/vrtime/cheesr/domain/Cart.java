package eu.vrtime.cheesr.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8415400886645685441L;
	
	private List<Cheese> cheeses = new ArrayList<Cheese>();
	private Address billingAddress = new Address();
	
	public Cart() {
		
	}

	public List<Cheese> getCheeses() {
		return cheeses;
	}

	public void setCheeses(List<Cheese> other) {
		this.cheeses = other;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address other) {
		this.billingAddress = other;
	}
	
	public double getTotal() {
		double total = 0;
		for (Cheese cheese : cheeses) {
			total += cheese.getPrice();
		}
		return total;
	}
	
	
	

}
