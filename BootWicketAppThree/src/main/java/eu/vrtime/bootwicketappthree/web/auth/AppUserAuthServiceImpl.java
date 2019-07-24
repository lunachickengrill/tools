package eu.vrtime.bootwicketappthree.web.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.vrtime.bootwicketappthree.model.AppUser;
import eu.vrtime.bootwicketappthree.repositories.AppUserRepository;

@Service
public class AppUserAuthServiceImpl implements AppUserAuthService {

	private AppUserRepository userRepo;

	@Autowired
	public AppUserAuthServiceImpl(AppUserRepository userRepo) {
		this.userRepo = userRepo;
	}

	/* (non-Javadoc)
	 * @see eu.vrtime.bootwicketappthree.web.auth.AppUserAuthService#checkLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean checkLogin(final String username, final String password) {
		Optional<AppUser> user = userRepo.findByUsername(username);

		return user.isPresent() ? user.get().getPassword().equals(password) : false;

	}
	
	/* (non-Javadoc)
	 * @see eu.vrtime.bootwicketappthree.web.auth.AppUserAuthService#testCheckLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean testCheckLogin(final String username, final String password) {
		System.out.println(username + " " + password);
		return true;
	}

}
