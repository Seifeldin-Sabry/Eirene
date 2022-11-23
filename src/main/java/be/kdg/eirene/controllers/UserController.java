package be.kdg.eirene.controllers;

import be.kdg.eirene.model.SessionType;
import be.kdg.eirene.model.User;
import be.kdg.eirene.service.CookieService;
import be.kdg.eirene.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.Duration;

@Controller
@RequestMapping ("/profile")
public class UserController {
	private final Logger logger;
	private final UserService userService;
	private final CookieService cookieService;

	public UserController(UserService userService, CookieService cookieService) {
		this.logger = LoggerFactory.getLogger(this.getClass());
		this.userService = userService;
		this.cookieService = cookieService;
	}

	@GetMapping
	public ModelAndView loadProfile(HttpSession session) {
		if (cookieService.cookieInvalid(session)) {
			return new ModelAndView("redirect:/");
		}
		User user = userService.getUser(cookieService.getAttribute(session));
		final ModelAndView modelAndView = new ModelAndView("profile").addObject(user);
		modelAndView
				.addObject("totalDuration", Duration.ofSeconds(userService.getTotalDuration(user.getUser_id())))
				.addObject("averageDuration", Duration.ofSeconds(userService.getAverageDuration(user.getUser_id())))
				.addObject("focus", SessionType.FOCUS)
				.addObject("meditation", SessionType.MEDITATION);
		return modelAndView;
	}
}
