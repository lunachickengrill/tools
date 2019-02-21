package eu.vrtime.cheesr.domain;

import java.io.Serializable;

public class Cheese implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1810844790123648895L;

	private String name = new String();
	private String description = new String();
	private double price;

	public Cheese(final String name, final String description, final double price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
