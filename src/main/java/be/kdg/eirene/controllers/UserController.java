package be.kdg.eirene.controllers;

import be.kdg.eirene.exceptions.UnauthorizedAccessException;
import be.kdg.eirene.model.Session;
import be.kdg.eirene.model.User;
import be.kdg.eirene.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.Duration;

@Controller
@RequestMapping ("/profile")
public class UserController {
	private final Logger logger;
	private final UserService userService;

	public UserController(UserService userService) {
		this.logger = LoggerFactory.getLogger(this.getClass());
		this.userService = userService;
	}

	@GetMapping ("/{userId}")
	public ModelAndView loadProfile(@PathVariable Long userId, HttpSession session) {
		if (session.getAttribute("user_id") == null) {
			return new ModelAndView(userService.redirectUnauthorized());
		}
		Long cookie = Long.parseLong(session.getAttribute("user_id").toString());
		if (!cookie.equals(userId)) {
			throw new UnauthorizedAccessException("You are not authorized to view this page");
		}
		User user = userService.getUser(Long.parseLong(session.getAttribute("user_id").toString()));
		final ModelAndView modelAndView = new ModelAndView("user").addObject(user);
		modelAndView.addObject("totalDuration", Duration.ofMillis(user.getSessionHistory()
		                                                              .stream()
		                                                              .filter(s -> s.getEndTime() != null)
		                                                              .mapToLong(Session::getDuration)
		                                                              .reduce(Long::sum).orElse(0)));
		return modelAndView;
	}
}
