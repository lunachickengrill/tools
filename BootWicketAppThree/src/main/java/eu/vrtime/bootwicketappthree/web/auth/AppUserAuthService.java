package eu.vrtime.bootwicketappthree.web.auth;

public interface AppUserAuthService {

	boolean checkLogin(String username, String password);

	boolean testCheckLogin(String username, String password);

}