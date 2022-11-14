package be.kdg.eirene.controllers;

import be.kdg.eirene.controllers.viewmodel.UserViewModel;
import be.kdg.eirene.model.Sex;
import be.kdg.eirene.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@RequestMapping ("/account")
public class AccountController {
	private final Logger logger;
	private final UserService userService;

	@Autowired
	public AccountController(UserService userService) {
		this.logger = LoggerFactory.getLogger(this.getClass());
		this.userService = userService;
	}

	@GetMapping ("/register")
	public ModelAndView register() {
		logger.info("register called");
		return new ModelAndView("register", "sexes", Sex.values());
	}

	/**
	 * Register the user in the user service
	 *
	 * @param user   - the Data Transfer Object we gather all the info from
	 * @param errors - Errors
	 * @return user page if succesful, register page if unsuccesful
	 */
	@PostMapping ("/register")
	public ModelAndView registerUser(@ModelAttribute ("user") @Valid UserViewModel user, BindingResult errors) {
		logger.info("registerUser called");
		// Print errors
		if (errors.hasErrors()) {
			errors.getAllErrors().forEach(error -> logger.error(error.toString()));
			return new ModelAndView("/register");
		}
		// Check passwords
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			logger.error("passwords don't match");
			return new ModelAndView("/register");
		}
		logger.info(String.valueOf(user));
		return new ModelAndView("redirect:/user");
	}

	@GetMapping ("/login")
	public ModelAndView login() {
		logger.info("login called");
		return new ModelAndView("account");
	}
}
