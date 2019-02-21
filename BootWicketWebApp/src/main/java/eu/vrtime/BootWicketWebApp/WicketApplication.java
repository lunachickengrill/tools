package eu.vrtime.BootWicketWebApp;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import eu.vrtime.BootWicketWebApp.web.CreateCustomerPage;
import eu.vrtime.BootWicketWebApp.web.HomePage;
import eu.vrtime.BootWicketWebApp.web.SearchPage;

public class WicketApplication extends WebApplication {

	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		super.init();
		getComponentInstantiationListeners().add(new SpringComponentInjector(this));
		mountPage("/search", SearchPage.class);
		mountPage("/create", HomePage.class);
		mountPage("createcustomer", CreateCustomerPage.class);
	}

}
