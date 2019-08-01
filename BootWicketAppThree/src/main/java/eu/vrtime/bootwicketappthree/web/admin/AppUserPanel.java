package eu.vrtime.bootwicketappthree.web.admin;

import org.apache.wicket.Application;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import eu.vrtime.bootwicketappthree.model.AppUser;
import eu.vrtime.bootwicketappthree.web.auth.AppAuthenticatedWebSession;

public class AppUserPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 382955354848113614L;
		
	private AppUser appUser;
	private IModel<String> firstname;
	private IModel<String> lastname;
	
	private AppAuthenticatedWebSession session = (AppAuthenticatedWebSession) AuthenticatedWebSession.get();

	public AppUserPanel(final String id) {
		super(id);
		session = (AppAuthenticatedWebSession) AuthenticatedWebSession.get();
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		add(new Label("lblFirstname", firstname));
		add(new Label("lblLastname", lastname));

	

	}

	@Override
	protected void onConfigure() {
		super.onConfigure();
		this.firstname = Model.of(new String(session.getAppUser().getFirstName()));
		this.lastname = Model.of(new String(session.getAppUser().getLastName()));
		
	}
	
	

}
