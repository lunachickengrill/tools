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

public class UserInfoPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 382955354848113614L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private AppUser appUser;
	private AppAuthenticatedWebSession session = (AppAuthenticatedWebSession) AuthenticatedWebSession.get();
	private Label lblUserFirstName;
	private Label lblUserLastName;

	public UserInfoPanel(final String id) {
		super(id);
		session = (AppAuthenticatedWebSession) AuthenticatedWebSession.get();

	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		lblUserFirstName = new Label("lblUserFirstName");
		lblUserLastName = new Label("lblUserLastName");
		add(lblUserFirstName);
		add(lblUserLastName);

	}

	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();
		if (session.isSignedIn()) {
			appUser = session.getAppUser();
			lblUserFirstName.setDefaultModel(new PropertyModel<AppUser>(appUser, "firstName"));
			lblUserLastName.setDefaultModel(new PropertyModel<AppUser>(appUser, "lastName"));
		}
	}

}
