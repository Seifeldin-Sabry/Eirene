package be.kdg.eirene.controllers;

import be.kdg.eirene.model.Sex;
import be.kdg.eirene.presenter.viewmodel.UserSignUpViewModel;
import be.kdg.eirene.service.UserService;
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
@RequestMapping ("/signup")
public class SignUpController {

	private final Logger logger;
	private final UserService userService;

	@Autowired
	public SignUpController(UserService userService) {
		this.logger = LoggerFactory.getLogger(this.getClass());
		this.userService = userService;
	}

	@GetMapping
	public ModelAndView loadSignUpForm() {
		logger.info("signup called");
		return new ModelAndView("signup", "sexes", Sex.values())
				.addObject("user", new UserSignUpViewModel());
	}

	@PostMapping
	public ModelAndView signUpUser(@ModelAttribute ("user") @Valid UserSignUpViewModel user, BindingResult errors) {
		logger.info("post signup called");
		if (errors.hasErrors()) {
			if (errors.hasGlobalErrors())
				errors.rejectValue("confirmPassword", "error.passwords.do.not.match",
						errors.getGlobalError().getDefaultMessage());
			errors.getAllErrors().forEach(error -> logger.error(error.toString()));
			return new ModelAndView("signup")
					.addObject("sexes", Sex.values());
		}
		//		userService.addUser(user.getName(), user.getEmail(), user.getPassword(), user.getSex());
		//TODO: using spring security we should be able to login the user here
		return new ModelAndView("redirect:user");
	}
}
