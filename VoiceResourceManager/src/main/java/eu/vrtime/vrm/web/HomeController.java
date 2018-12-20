package eu.vrtime.vrm.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@Value("${app.message}")
	private String message;

	@GetMapping("/")
	public String root() {
		return "/user/index";
	}

	@GetMapping("/user")
	public String userIndex() {
		return "/user/index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/access-denied")
	public String accessDenied() {
		return "/error/access-denied";
	}

}
