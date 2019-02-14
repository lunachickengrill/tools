package eu.vrtime.cheesr;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import eu.vrtime.cheesr.domain.Cheese;
import eu.vrtime.cheesr.session.CheesrSession;
import eu.vrtime.cheesr.web.Index;

public class CheesrWicketApplication extends WebApplication {
	private List<Cheese> cheeses = Arrays.asList(new Cheese("Gouda", "Gouda is y yellowish Dutch[...]", 1.65),
			new Cheese("Edam", "Edam (Dutch Edammer) is a D[...]", 1.05),
			new Cheese("Maasdam", "Maasdam cheese is a Dutc[...]", 2.35),
			new Cheese("Brie", "Brie is a soft cows' milk c[...]", 3.15),
			new Cheese("Buxton Blue", "Buxton Blue cheese i[...]", 0.99),
			new Cheese("Parmesan", "Parmesan is a grana che[...]", 1.99),
			new Cheese("Cheddar", "Cheddar cheese is a hard[...]", 2.95),
			new Cheese("Roquefort", "Roquefort is a ewe's-m[...]", 1.67),
			new Cheese("Boursin", "Boursin cheese is a soft[...]", 1.33),
			new Cheese("Camembert", "Camembert is a soft, c[...]", 1.69),
			new Cheese("Emmental", "Emmental is yellow, mil[...]", 2.39),
			new Cheese("Reblochon", "Reblochon is a French [...]", 2.99));

	/**
	 * Constructor
	 */
	public CheesrWicketApplication() {
	}

	@Override
	protected void init() {
		super.init();
		getComponentInstantiationListeners().add(new SpringComponentInjector(this));
	}

	public static CheesrWicketApplication get() {
		return (CheesrWicketApplication) Application.get();
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return Index.class;
	}

	public List<Cheese> getCheeses() {
		return Collections.unmodifiableList(cheeses);
	}

	@Override
	public Session newSession(Request request, Response response) {
		return new CheesrSession(request);
	}
	
	

}
