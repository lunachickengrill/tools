package eu.vrtime.cheesr.web;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;

import eu.vrtime.cheesr.CheesrWicketApplication;
import eu.vrtime.cheesr.domain.Cart;
import eu.vrtime.cheesr.domain.Cheese;
import eu.vrtime.cheesr.session.CheesrSession;

public abstract class CheesrPage extends WebPage{
	
	
	public CheesrSession getCheesrSession() {
		return (CheesrSession) getSession();
	}
	
	public Cart getCart() {
		return getCheesrSession().getCart();
	}
	
	public List<Cheese> getCheeses(){
		return CheesrWicketApplication.get().getCheeses();
	}

}
