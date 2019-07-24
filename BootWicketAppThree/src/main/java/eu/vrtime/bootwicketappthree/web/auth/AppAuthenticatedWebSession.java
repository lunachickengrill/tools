package eu.vrtime.bootwicketappthree.web.auth;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.Assert;

public class AppAuthenticatedWebSession extends AuthenticatedWebSession {

	private static final long serialVersionUID = -8841219806164597147L;

	@SpringBean
	@Qualifier("authService")
	private AppUserAuthService authService;

	public AppAuthenticatedWebSession(Request request) {
		super(request);
	}

	@Override
	protected boolean authenticate(String username, String password) {
//		return (username.equals("tom") && password.equals("lgs123"));
		Assert.notNull(username, "username is null");
		Assert.notNull(password, "password is null");
		Assert.notNull(authService, "authService not injected oO... why???");
		return authService.checkLogin(username, password);

	}

	@Override
	public Roles getRoles() {
		return new Roles();
	}

}
