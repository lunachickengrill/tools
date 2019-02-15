package eu.vrtime.cheesr.web;

import org.apache.wicket.markup.html.panel.Panel;

import eu.vrtime.cheesr.domain.Cart;

public class ShoppingCartPanel extends Panel {
	
	private Cart cart;
	
	public ShoppingCartPanel(String id, Cart cart) {
		super(id);
		this.cart=cart;
	}
	
	private Cart getCart() {
		return this.cart;
	}

}
