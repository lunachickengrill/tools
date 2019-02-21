package eu.vrtime.cheesr.session;

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import eu.vrtime.cheesr.domain.Cart;

public class CheesrSession extends WebSession {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1328627970334053849L;
	private Cart cart = new Cart();

	public CheesrSession(Request request) {
		super(request);
	}
	
	public Cart getCart() {
		return cart;
	}

}
