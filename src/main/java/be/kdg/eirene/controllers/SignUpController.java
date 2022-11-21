package be.kdg.eirene.controllers;

import be.kdg.eirene.model.Gender;
import be.kdg.eirene.model.User;
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

import javax.servlet.http.HttpSession;
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
	public ModelAndView loadSignUpForm(HttpSession session) {
		logger.info("get loadSignUpForm called");
		logger.info("session: " + session.getAttribute("user_id"));
		if (session.getAttribute("user_id") != null) {
			return new ModelAndView("redirect:/profile/" + session.getAttribute("user_id"));
		}
		return new ModelAndView("signup", "genders", Gender.values())
				.addObject("user", new UserSignUpViewModel());
	}

	@PostMapping
	public ModelAndView signUpUser(@ModelAttribute ("user") @Valid UserSignUpViewModel user, BindingResult errors, HttpSession session) {
		if (errors.hasErrors()) {
			if (errors.hasGlobalErrors())
				errors.rejectValue("confirmPassword", "error.passwords.do.not.match",
						errors.getGlobalError().getDefaultMessage());
			errors.getAllErrors().forEach(error -> logger.error(error.toString()));
			return new ModelAndView("signup")
					.addObject("genders", Gender.values());
		}
		User userDB = userService.addUser(user.getName(), user.getEmail(), user.getPassword(), user.getGender());
		session.setAttribute("user_id", userDB.getUser_id());
		return new ModelAndView("redirect:/profile/" + session.getAttribute("user_id"));
	}
}
