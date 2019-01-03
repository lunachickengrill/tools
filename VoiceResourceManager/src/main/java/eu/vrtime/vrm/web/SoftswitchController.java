package eu.vrtime.vrm.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import eu.vrtime.vrm.domain.model.SessionManager;
import eu.vrtime.vrm.domain.model.Softswitch;
import eu.vrtime.vrm.repositories.SessionManagerRepository;
import eu.vrtime.vrm.repositories.SoftswitchRepository;

@Controller
public class SoftswitchController {

	@Autowired
	SessionManagerRepository sessionManagerRepo;

	@Autowired
	SoftswitchRepository switchRepository;

	@Value("${app.message}")
	private String message;

	@PostMapping("/querySoftswitches")
	public String querySoftswitch(@Valid @ModelAttribute("softswitch") Softswitch softswitch, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			System.out.println("Binding error");
			return "main";
		}
		
		if (softswitch.getName() != null && softswitch.getSwitchId() == null) {
			List<Softswitch> dbSw = switchRepository.findByNameIgnoreCaseContains(softswitch.getName());
			model.addAttribute("softswitches", dbSw);
		}
		if (softswitch.getSwitchId() != null) {
			Optional<Softswitch> dbSw = switchRepository.findBySwitchId(softswitch.getSwitchId());
			if (dbSw.isPresent()) {
				model.addAttribute("softswitches", dbSw.get());
			}
		}
		
		if (softswitch.getSwitchId() ==null && softswitch.getName() == null) {
			List<Softswitch> dbSw = switchRepository.findAll();
			model.addAttribute("softswitches", dbSw);
		}

		return "main";
	}
	
	@ModelAttribute("softswitch")
	public Softswitch getSoftswitch() {
		return new Softswitch();
	}


	@GetMapping("/main")
	public ModelAndView softswitchMain() {
		return new ModelAndView("main", "softswitch", new Softswitch());
	}

}
