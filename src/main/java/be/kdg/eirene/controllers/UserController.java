package be.kdg.eirene.controllers;

import be.kdg.eirene.model.Gender;
import be.kdg.eirene.model.SessionType;
import be.kdg.eirene.model.User;
import be.kdg.eirene.presenter.viewmodel.PasswordEditViewModel;
import be.kdg.eirene.presenter.viewmodel.UserEditViewModel;
import be.kdg.eirene.repository.Period;
import be.kdg.eirene.service.CookieService;
import be.kdg.eirene.service.SessionService;
import be.kdg.eirene.service.UserService;
import be.kdg.eirene.util.PrettyTimeUtil;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.Duration;

@Controller
@RequestMapping ("/profile")
public class UserController {
	private final Logger logger;
	private final UserService userService;
	private final SessionService sessionService;
	private final CookieService cookieService;

	public UserController(UserService userService, CookieService cookieService, SessionService sessionService) {
		this.logger = LoggerFactory.getLogger(this.getClass());
		this.userService = userService;
		this.cookieService = cookieService;
		this.sessionService = sessionService;
	}

	@GetMapping
	public ModelAndView loadProfile(HttpSession session) {
		if (cookieService.cookieInvalid(session)) {
			return new ModelAndView("redirect:/");
		}
		User user = userService.getUser(cookieService.getAttribute(session));
		final ModelAndView modelAndView = new ModelAndView("profile").addObject(user);
		modelAndView
				.addObject("totalDuration", PrettyTimeUtil.getPrettyDuration(Duration.ofSeconds(userService.getTotalDuration(user.getUser_id()))))
				.addObject("averageDuration", PrettyTimeUtil.getPrettyDuration(Duration.ofSeconds(userService.getAverageDuration(user.getUser_id()))))
				.addObject("focus", SessionType.FOCUS)
				.addObject("meditation", SessionType.MEDITATION)
				.addObject("sessionCount", sessionService.getSessionsCount(user.getUser_id()));
		return modelAndView;
	}

	@GetMapping ("/edit")
	public ModelAndView editProfile(HttpSession session) {
		if (cookieService.cookieInvalid(session)) {
			return new ModelAndView("redirect:/");
		}
		final User user = userService.getUser(cookieService.getAttribute(session));
		return new ModelAndView("profile-edit")
				.addObject("genders", Gender.values())
				.addObject("viewModel", new UserEditViewModel(user.getName(), user.getEmail(), user.getGender()));
	}

	@PutMapping ("/edit")
	public ModelAndView updateProfile(@ModelAttribute ("viewModel") @Valid UserEditViewModel viewModel, BindingResult errors, HttpSession session) {
		final User user = userService.getUser(cookieService.getAttribute(session));
		boolean emailIsDifferentButIsTaken = !userService.newEmailIsCurrentEmail(user, viewModel.getEmail()) && userService.emailExists(viewModel.getEmail());
		if (errors.hasErrors()) {
			errors.getAllErrors().forEach(error -> logger.error(error.toString()));
			if (emailIsDifferentButIsTaken) errors.rejectValue("email", "409", "Email already exists");
			return new ModelAndView("profile-edit")
					.addObject("genders", Gender.values());
		}
		if (emailIsDifferentButIsTaken) {
			errors.rejectValue("email", "409", "Email already exists");
			return new ModelAndView("profile-edit")
					.addObject("genders", Gender.values());
		}
		userService.updateProfile(user.getUser_id(), viewModel.getName(), viewModel.getEmail(), viewModel.getGender());
		return new ModelAndView("redirect:/profile");
	}

	@GetMapping ("/pw")
	public ModelAndView editPassword(HttpSession session) {
		if (cookieService.cookieInvalid(session)) {
			return new ModelAndView("redirect:/");
		}
		return new ModelAndView("profile-pw")
				.addObject("viewModel", new PasswordEditViewModel());
	}

	@PutMapping ("/pw")
	public ModelAndView updatePassword(@ModelAttribute ("viewModel") @Valid PasswordEditViewModel viewModel, BindingResult errors, HttpSession session) {
		final User user = userService.getUser(cookieService.getAttribute(session));
		boolean wrongOldPassword = !userService.passwordMatches(user, viewModel.getOldPassword());
		if (errors.hasErrors()) {
			if (errors.hasGlobalErrors())
				errors.rejectValue("confirmPassword", "422",
						errors.getGlobalError().getDefaultMessage());
			errors.getAllErrors().forEach(error -> logger.error(error.toString()));
			if (wrongOldPassword) errors.rejectValue("oldPassword", "403", "Old password is incorrect");
			return new ModelAndView("profile-pw");
		}
		if (wrongOldPassword) {
			errors.rejectValue("oldPassword", "403", "Old password is incorrect");
			return new ModelAndView("profile-pw");
		}
		userService.updatePassword(user.getUser_id(), viewModel.getPassword());
		return new ModelAndView("redirect:/profile");
	}

	@GetMapping ("/analytics")
	public ModelAndView loadAnalytics(HttpSession session) {
		if (cookieService.cookieInvalid(session)) {
			return new ModelAndView("redirect:/");
		}
		Gson gson = new Gson();
		User user = userService.getUser(cookieService.getAttribute(session));
		return new ModelAndView("profile-analytics")
				.addObject("dayFocus", gson.toJson(sessionService.getReadings(user.getUser_id(), Period.DAY, SessionType.FOCUS)))
				.addObject("weekFocus", gson.toJson(sessionService.getReadings(user.getUser_id(), Period.WEEK, SessionType.FOCUS)))
				.addObject("monthFocus", gson.toJson(sessionService.getReadings(user.getUser_id(), Period.MONTH, SessionType.FOCUS)))
				.addObject("yearFocus", gson.toJson(sessionService.getReadings(user.getUser_id(), Period.YEAR, SessionType.FOCUS)))
				.addObject("allFocus", gson.toJson(sessionService.getReadings(user.getUser_id(), Period.ALL, SessionType.FOCUS)))
				.addObject("dayMeditation", gson.toJson(sessionService.getReadings(user.getUser_id(), Period.DAY, SessionType.MEDITATION)))
				.addObject("weekMeditation", gson.toJson(sessionService.getReadings(user.getUser_id(), Period.WEEK, SessionType.MEDITATION)))
				.addObject("monthMeditation", gson.toJson(sessionService.getReadings(user.getUser_id(), Period.MONTH, SessionType.MEDITATION)))
				.addObject("yearMeditation", gson.toJson(sessionService.getReadings(user.getUser_id(), Period.YEAR, SessionType.MEDITATION)))
				.addObject("allMeditation", gson.toJson(sessionService.getReadings(user.getUser_id(), Period.ALL, SessionType.MEDITATION)));
	}

	@DeleteMapping
	public ModelAndView deleteProfile(HttpSession session) {
		if (cookieService.cookieInvalid(session)) {
			return new ModelAndView("redirect:/");
		}
		userService.deleteUser(cookieService.getAttribute(session));
		cookieService.removeCookie(session);
		return new ModelAndView("redirect:/");
	}
}
