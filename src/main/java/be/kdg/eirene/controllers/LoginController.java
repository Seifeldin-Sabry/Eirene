package be.kdg.eirene.controllers;

import be.kdg.eirene.model.User;
import be.kdg.eirene.presenter.viewmodel.UserLoginViewModel;
import be.kdg.eirene.service.UserService;
import be.kdg.eirene.util.BcryptPasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping ("/login")
public class LoginController {

	private final Logger logger;
	private final UserService userService;

	@Autowired
	public LoginController(UserService userService) {
		this.logger = LoggerFactory.getLogger(this.getClass());
		this.userService = userService;
	}

	@GetMapping
	public ModelAndView loadLoginForm() {
		logger.info("login called");
		return new ModelAndView("login")
				.addObject("user", new UserLoginViewModel());
	}

	@PostMapping
	public ModelAndView loginUser(@ModelAttribute ("user") @Valid UserLoginViewModel user, BindingResult errors) {
		logger.info("post loginUser called");
		if (errors.hasErrors()) {
			errors.getAllErrors().forEach(error -> logger.error(error.toString()));
			return new ModelAndView("login");
		}
		logger.info(String.valueOf(user));
		// 1. Check if user exists
		User retrievedUser = userService.getUser(user.getEmail());
		if (retrievedUser == null) {
			logger.error("user not found");
			errors.rejectValue("email", "user.not.found");
			return new ModelAndView("login");
		}
		// 2. Check if password is correct
		if (!BcryptPasswordUtil.checkPassword(user.getPassword(), retrievedUser.getPassword())) {
			logger.error("password incorrect");
			errors.rejectValue("password", "email.or.password.incorrect");
			errors.rejectValue("email", "email.or.password.incorrect");
			return new ModelAndView("login");
		}
		logger.info("login succesful");
		//TODO: using spring security we should be able to login the user here
		return new ModelAndView("redirect:/user");
	}

}
