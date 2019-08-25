package eu.vrtime.bootwicketappthree.web.admin;

import org.apache.wicket.Application;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.vrtime.bootwicketappthree.model.AppUser;
import eu.vrtime.bootwicketappthree.web.auth.AppAuthenticatedWebSession;

public class AppUserPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 382955354848113614L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private AppUser appUser;
	private IModel<String> user;

	private AppAuthenticatedWebSession session = (AppAuthenticatedWebSession) AuthenticatedWebSession.get();

	public AppUserPanel(final String id) {
		super(id);
		session = (AppAuthenticatedWebSession) AuthenticatedWebSession.get();

	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		if (session.isSignedIn()) {
			appUser = session.getAppUser();
			add(new Label("lblUser", appUser.getUsername()));
		}
		logger.info("Session user " + appUser.getUsername() + " is signed in");

	}

}
