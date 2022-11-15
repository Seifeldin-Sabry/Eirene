package be.kdg.eirene.controllers;

import be.kdg.eirene.presenter.viewmodel.UserLoginViewModel;
import be.kdg.eirene.presenter.viewmodel.UserSignUpViewModel;
import be.kdg.eirene.model.Sex;
import be.kdg.eirene.model.User;
import be.kdg.eirene.service.UserService;
import be.kdg.eirene.util.BcryptPasswordUtil;
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
		return new ModelAndView("register", "sexes", Sex.values())
				.addObject("user", new UserSignUpViewModel());
	}

	/**
	 * Register the user in the user service
	 *
	 * @param user   - the Data Transfer Object we gather all the info from
	 * @param errors - Errors
	 * @return user page if succesful, register page if unsuccesful
	 */
	@PostMapping ("/register")
	public ModelAndView registerUser(@ModelAttribute ("user") @Valid UserSignUpViewModel user, BindingResult errors) {
		logger.info("post registerUser called");
		if (errors.hasErrors()) {
			if (errors.hasGlobalErrors())
				errors.rejectValue("confirmPassword", "error.passwords.do.not.match", errors.getGlobalError()
				                                                                            .getDefaultMessage());
			errors.getAllErrors().forEach(error -> logger.error(error.toString()));
			return new ModelAndView("register")
					.addObject("sexes", Sex.values());
		}
		//		userService.addUser(user.getName(), user.getEmail(), user.getPassword(), user.getSex());
		//TODO: using spring security we should be able to login the user here
		return new ModelAndView("redirect:user");
	}

	@GetMapping
	public ModelAndView login() {
		logger.info("login called");
		return new ModelAndView("account")
				.addObject("user", new UserLoginViewModel());
	}

	@PostMapping
	public ModelAndView loginUser(@ModelAttribute ("user") @Valid UserLoginViewModel user, BindingResult errors) {
		logger.info("post loginUser called");
		if (errors.hasErrors()) {
			errors.getAllErrors().forEach(error -> logger.error(error.toString()));
			return new ModelAndView("account");
		}
		logger.info(String.valueOf(user));
		// 1. Check if user exists
		User retrievedUser = userService.getUser(user.getEmail());
		if (retrievedUser == null) {
			logger.error("user not found");
			errors.rejectValue("email", "user.not.found");
			return new ModelAndView("account");
		}
		// 2. Check if password is correct
		if (!BcryptPasswordUtil.checkPassword(user.getPassword(), retrievedUser.getPassword())) {
			logger.error("password incorrect");
			errors.rejectValue("password", "email.or.password.incorrect");
			errors.rejectValue("email", "email.or.password.incorrect");
			return new ModelAndView("account");
		}
		logger.info("login succesful");
		//TODO: using spring security we should be able to login the user here
		return new ModelAndView("redirect:/user");
	}
}
