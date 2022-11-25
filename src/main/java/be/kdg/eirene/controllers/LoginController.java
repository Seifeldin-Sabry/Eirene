package be.kdg.eirene.controllers;

import be.kdg.eirene.model.User;
import be.kdg.eirene.presenter.viewmodel.UserLoginViewModel;
import be.kdg.eirene.service.CookieService;
import be.kdg.eirene.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {

	private final Logger logger;
	private final UserService userService;

	private final CookieService cookieService;

	@Autowired
	public LoginController(UserService userService, CookieService cookieService) {
		this.logger = LoggerFactory.getLogger(this.getClass());
		this.userService = userService;
		this.cookieService = cookieService;
	}

	@GetMapping ("/login")
	public ModelAndView loadLoginForm(HttpSession session) {
		if (!cookieService.cookieInvalid(session)) {
			return new ModelAndView("redirect:/profile");
		}
		return new ModelAndView("login")
				.addObject("user", new UserLoginViewModel());
	}

	@PostMapping ("/login")
	public ModelAndView loginUser(@ModelAttribute ("user") @Valid UserLoginViewModel user, BindingResult errors, HttpSession session) {
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
			errors.rejectValue("password", "404", "Invalid email or password");
			errors.rejectValue("email", "404", "Invalid email or password");
			return new ModelAndView("login");
		}
		// 2. Check if password is correct
		if (!userService.passwordMatches(retrievedUser, user.getPassword())) {
			logger.error("password incorrect");
			errors.rejectValue("password", "404", "Invalid email or password");
			errors.rejectValue("email", "404", "Invalid email or password");
			return new ModelAndView("login");
		}
		logger.info("login succesful");
		cookieService.setCookie(session, retrievedUser.getUser_id());
		return new ModelAndView("redirect:/profile/");
	}

	@GetMapping ("/logout")
	public ModelAndView logoutUser(HttpSession session) {
		session.invalidate();
		return new ModelAndView("redirect:/");
	}
}
