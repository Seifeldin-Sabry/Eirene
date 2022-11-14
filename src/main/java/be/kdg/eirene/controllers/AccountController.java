package be.kdg.eirene.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping ("/account")
public class AccountController {
	private final Logger logger;

	@Autowired
	public AccountController() {
		this.logger = LoggerFactory.getLogger(this.getClass());
	}

	@GetMapping("/login")
	public ModelAndView showLogin() {
		logger.info("Controller is running showLogin");
		return new ModelAndView("login");
	}
}
