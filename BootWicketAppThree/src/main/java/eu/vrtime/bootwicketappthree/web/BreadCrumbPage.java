package eu.vrtime.bootwicketappthree.web;

import org.apache.wicket.Application;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.extensions.breadcrumb.BreadCrumbBar;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

import eu.vrtime.bootwicketappthree.web.login.LoginPage;

public class BreadCrumbPage extends WebPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8819638763542951678L;

	private static final String PANEL_ID = "panel";
	private static final String BREADCRUMBPBAR_ID = "breadCrumbBar";
	private static final String LOGOUTLINK_ID = "logout";

	public BreadCrumbPage() {
		super();

		BreadCrumbBar breadCrumbBar = new BreadCrumbBar(BREADCRUMBPBAR_ID);
		add(breadCrumbBar);
		FirstBreadCrumbPanel firstpanel = new FirstBreadCrumbPanel(PANEL_ID, breadCrumbBar);
		add(firstpanel);
		breadCrumbBar.setActive(firstpanel);
		add(createLogoutLink(LOGOUTLINK_ID));

	}

	@Override
	protected void onConfigure() {
		super.onConfigure();
	}

	private Link<Void> createLogoutLink(final String id) {
		Link<Void> logout = new Link<Void>(id) {

			@Override
			public void onClick() {
				AuthenticatedWebSession.get().invalidateNow();
				setResponsePage(LoginPage.class);

			}

		};
		return logout;
	}

}
