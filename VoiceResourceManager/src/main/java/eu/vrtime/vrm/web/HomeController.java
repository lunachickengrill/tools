package eu.vrtime.vrm.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import eu.vrtime.vrm.domain.model.SessionManager;
import eu.vrtime.vrm.repositories.SessionManagerRepository;

@Controller
public class HomeController {
	
	@Autowired
	SessionManagerRepository sessionManagerRepo;

	@Value("${app.message}")
	private String message;

	@GetMapping("/")
	public ModelAndView root() {
		ModelAndView modelAndView = new ModelAndView("/user/index");
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		String formattedDate = dateFormat.format(date);
		modelAndView.addObject("serverTime", formattedDate);
		modelAndView.addObject("test", "SDFASDAS");
		
		List<SessionManager> sessionManagers = sessionManagerRepo.findAll();
		modelAndView.addObject("sessionManagers", sessionManagers);
		return modelAndView;
	}

	@GetMapping("/user")
	public String userIndex() {
		return "/user/index";
	}
	
	@GetMapping("/main")
	public String login() {
		return ("/main");
	}

}
