package be.kdg.eirene.controllers;

import be.kdg.eirene.service.CookieService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

	private final CookieService cookieService;

	public MainController(CookieService cookieService) {
		this.cookieService = cookieService;
	}

	@GetMapping ("/")
	public ModelAndView index(HttpSession session) {
		boolean loggedIn = !cookieService.cookieInvalid(session);
		return new ModelAndView("index").addObject("loggedIn", loggedIn);
	}

	@GetMapping ("/terms-of-service")
	public ModelAndView termsOfService() {
		return new ModelAndView("terms-of-service");
	}

	@GetMapping ("/wiki")
	public ModelAndView wiki() {
		return new ModelAndView("wiki");
	}
}
