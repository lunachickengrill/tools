package eu.vrtime.bootwicketappthree.web.login;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.Strings;

import eu.vrtime.bootwicketappthree.web.admin.AdminPage;
import eu.vrtime.bootwicketappthree.web.auth.AppAuthenticatedWebSession;

public class LoginPage extends WebPage {
	
	private static final String USERNAME_ID = "username";
	private static final String PASSWORD_ID = "password";
	private static final String BUTTON_ID ="login";

	private String username;
	private String password;

	public LoginPage() {
		super();
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(createForm("form"));
	}

	private StatelessForm<Void> createForm(final String id) {
		
		StatelessForm<Void> form = new StatelessForm<Void>(id);
		form.setDefaultModel(new CompoundPropertyModel(this));
		
		TextField usernameTf = new TextField(USERNAME_ID);
		usernameTf.add(new AttributeModifier("placeholder", "Username"));
		form.add(usernameTf);
		
		PasswordTextField passwordTf = new PasswordTextField(PASSWORD_ID);
		passwordTf.add(new AttributeModifier("placeholder", "Password"));
		form.add(passwordTf);		

		form.add(new Button(BUTTON_ID) {

			@Override
			public void onSubmit() {
				if (Strings.isEmpty(username) || Strings.isEmpty(password))
					return;				

				boolean authResult = getCurrentSession().signIn(username, password);

				if (authResult)
//					continueToOriginalDestination();
					setResponsePage(AdminPage.class);
			}

		});

		return form;

	}
	
	private AppAuthenticatedWebSession getCurrentSession() {
		return (AppAuthenticatedWebSession) AuthenticatedWebSession.get();
	}

}
