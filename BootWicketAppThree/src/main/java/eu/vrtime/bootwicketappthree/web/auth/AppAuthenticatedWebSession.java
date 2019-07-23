package eu.vrtime.bootwicketappthree.web.auth;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class AppAuthenticatedWebSession extends AuthenticatedWebSession {

	private static final long serialVersionUID = -8841219806164597147L;

	@SpringBean
	private AppUserAuthService authService;

	public AppAuthenticatedWebSession(Request request) {
		super(request);
	}

	@Override
	protected boolean authenticate(String username, String password) {
		return authService.checkLogin(username, password);
	}

	@Override
	public Roles getRoles() {
		return new Roles();
	}

}
