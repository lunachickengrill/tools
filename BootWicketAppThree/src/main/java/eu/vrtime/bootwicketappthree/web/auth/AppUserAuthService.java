package eu.vrtime.bootwicketappthree.web.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.vrtime.bootwicketappthree.model.AppUser;
import eu.vrtime.bootwicketappthree.repositories.AppUserRepository;

@Service
public class AppUserAuthService {

	private AppUserRepository userRepo;

	@Autowired
	public AppUserAuthService(AppUserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public boolean checkLogin(final String username, final String password) {
		Optional<AppUser> user = userRepo.findByUsername(username);

		return user.isPresent() ? user.get().getPassword().equals(password) : false;

	}

}
