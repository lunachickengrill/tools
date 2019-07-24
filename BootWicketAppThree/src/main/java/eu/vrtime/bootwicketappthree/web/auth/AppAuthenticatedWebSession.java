package eu.vrtime.bootwicketappthree.web.auth;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
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
		
//		Injecting Spring Beans with @SpringBean annotation works ONLY on wicket componets. Any other wicket object like session or model need to inject manually		
		Injector.get().inject(this);
	}

	@Override
	protected boolean authenticate(String username, String password) {
		Assert.notNull(username, "username is null");
		Assert.notNull(password, "password is null");
		Assert.notNull(authService, "authService not injected in AppAuthenticatedWebSession oO... why the hell???");
		return authService.checkLogin(username, password);

	}

	@Override
	public Roles getRoles() {
		return new Roles();
	}

}
