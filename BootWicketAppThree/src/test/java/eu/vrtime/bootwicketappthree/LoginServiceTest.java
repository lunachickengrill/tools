package eu.vrtime.bootwicketappthree;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import eu.vrtime.bootwicketappthree.model.AppUser;
import eu.vrtime.bootwicketappthree.repositories.AppUserRepository;
import eu.vrtime.bootwicketappthree.web.auth.AppUserAuthService;

public class LoginServiceTest extends AbstractTestBase {

	public AppUser appUser = new AppUser("Tom", "Turbo", "tom", "lgs123");

	@Autowired
	private AppUserRepository userRepo;

	@Autowired
	private AppUserAuthService authService;

	@Test
	public void createAppUserTest() {
		AppUser dbAppUser = userRepo.saveAndFlush(appUser);
		assertNotNull(dbAppUser);

		assertNotNull(dbAppUser.getId());
		
		userRepo.delete(dbAppUser);

	}

	@Test
	public void appUserLoginTest() {
		AppUser dbAppUser = userRepo.saveAndFlush(appUser);

		Boolean isAuthenticated = authService.checkLogin("tom", "lgs123");
		assertTrue(isAuthenticated);

		isAuthenticated = authService.checkLogin("tom", "asdfasdf");
		assertFalse(isAuthenticated);

		isAuthenticated = authService.checkLogin("asdfasd", "lgs123");
		assertFalse(isAuthenticated);
		
		userRepo.delete(dbAppUser);

	}

}
