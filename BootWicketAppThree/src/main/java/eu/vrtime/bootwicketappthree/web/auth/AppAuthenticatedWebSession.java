package eu.vrtime.bootwicketappthree.web.auth;

import java.util.Optional;

import javax.inject.Inject;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.Assert;

import eu.vrtime.bootwicketappthree.model.AppUser;
import eu.vrtime.bootwicketappthree.repositories.AppRoles;
import eu.vrtime.bootwicketappthree.repositories.AppUserRepository;

public class AppAuthenticatedWebSession extends AuthenticatedWebSession {

	private static final long serialVersionUID = -8841219806164597147L;

	@Inject
	@Qualifier("authService")
	private AppUserAuthService authService;

	@Inject
	@Qualifier("AppUserRepository")
	private AppUserRepository userRepo;

	private AppUser appUser;

	public static AppAuthenticatedWebSession get() {
		return (AppAuthenticatedWebSession) Session.get();
	}

	public AppAuthenticatedWebSession(Request request) {
		super(request);

//		Injecting Spring Beans with @SpringBean annotation works ONLY on wicket componets. 
//		Any other wicket object like session or model need to inject manually		
		Injector.get().inject(this);
	}

	@Override
	public boolean authenticate(String username, String password) {
		Assert.notNull(username, "username is null");
		Assert.notNull(password, "password is null");
		Assert.notNull(authService, "authService not injected in AppAuthenticatedWebSession oO... why the hell???");
		Assert.notNull(userRepo, "userRepo not injected in AppAuthenticatedWebSession");

		if (authService.checkLogin(username, password)) {

			appUser = userRepo.findByUsername(username).get();

			return true;

		} else {

			return false;
		}

	}

	@Override
	public Roles getRoles() {
		Roles roles = new Roles();

		if (isSignedIn()) {
			roles.add(AppRoles.SIGNED_IN.toString());
		}
		roles.add(appUser.getRole().toString());

		return roles;

	}

	public boolean isAdmin() {
		return appUser.getRole().equals(AppRoles.ADMIN);
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

}
