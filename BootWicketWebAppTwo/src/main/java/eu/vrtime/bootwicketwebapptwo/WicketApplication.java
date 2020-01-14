package eu.vrtime.bootwicketwebapptwo;

import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import eu.vrtime.bootwicketwebapptwo.web.AdminPage;
import eu.vrtime.bootwicketwebapptwo.web.SandboxPage;

public class WicketApplication extends WebApplication {

	@Override
	protected void init() {
		super.init();
		getComponentInstantiationListeners().add(new SpringComponentInjector(this));
		mountPage("/admin", AdminPage.class);
		mountPage("/sandbox", SandboxPage.class);
		System.out.println("INIT");
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return AdminPage.class;
	}

	public static WicketApplication get() {
		return (WicketApplication) Application.get();
	}

}
