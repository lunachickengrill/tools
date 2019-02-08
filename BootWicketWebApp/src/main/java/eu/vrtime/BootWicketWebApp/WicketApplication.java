package eu.vrtime.BootWicketWebApp;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

import eu.vrtime.BootWicketWebApp.web.HomePage;

public class WicketApplication extends WebApplication {

	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}

}
