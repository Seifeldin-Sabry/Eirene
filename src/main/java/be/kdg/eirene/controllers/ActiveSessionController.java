package be.kdg.eirene.controllers;

import be.kdg.eirene.model.Reading;
import be.kdg.eirene.model.Session;
import be.kdg.eirene.model.SessionType;
import be.kdg.eirene.model.User;
import be.kdg.eirene.presenter.viewmodel.SessionFeedback;
import be.kdg.eirene.service.CookieService;
import be.kdg.eirene.service.SessionService;
import be.kdg.eirene.service.UserService;
import be.kdg.eirene.util.RequestDecryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Nullable;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping ("/newsession")
public class ActiveSessionController {

	private final CookieService cookieService;
	private final SessionService sessionService;
	private final UserService userService;
	private final Logger logger;
	private final RequestDecryptor decryptor;
	private Session session;

	@Autowired
	public ActiveSessionController(RequestDecryptor decryptor, CookieService cookieService, UserService userService, SessionService sessionService) {
		this.decryptor = decryptor;
		this.cookieService = cookieService;
		this.userService = userService;
		this.sessionService = sessionService;
		logger = LoggerFactory.getLogger(this.getClass());
	}

	@GetMapping
	public ModelAndView showNewSession(@RequestParam SessionType type, HttpSession httpSession) {
		if (cookieService.cookieInvalid(httpSession)) {
			return new ModelAndView("redirect:/");
		}
		Boolean sessionActive = cookieService.isSessionActive(httpSession);
		if (sessionActive == null || !sessionActive)
			cookieService.setSessionCookie(httpSession, Boolean.TRUE);
		User user = userService.getUser(cookieService.getAttribute(httpSession));
		session = sessionService.save(type, user);
		user.setSession(session);
		logger.info(session.toString());
		return new ModelAndView("active-session").addObject("type", type)
		                                         .addObject("session", session);
	}

	@PostMapping
	public ModelAndView getData(@RequestBody Reading data, HttpSession httpSession) {
		if (cookieService.isSessionActive(httpSession) != null && !cookieService.isSessionActive(httpSession))
			return new ModelAndView("redirect:/profile");
		//TODO: check for null because of order of action by the datasender
		session.addReading(data);
		logger.info(session.getReadings().toString());
		logger.info("Data received: " + data);
		return new ModelAndView("active-session").addObject("type", session.getType());
	}

	@GetMapping ("/stopsession")
	public ModelAndView stopSession(HttpSession httpSession) {
		session.stop();
		return new ModelAndView("feedback").addObject("sessionFeedback", new SessionFeedback());
	}

	@Deprecated
	@PostMapping ("/submit-feedback")
	public ModelAndView submit(@ModelAttribute ("sessionFeedback") @Valid SessionFeedback sessionFeedback, @Nullable @RequestParam ("feedback") String[] feedback, BindingResult errors, HttpSession httpSession) {
		if (cookieService.cookieInvalid(httpSession)) {
			return new ModelAndView("redirect:/");
		}
		logger.info("request radio ", feedback.toString());
		logger.info(sessionFeedback.getSessionName());
		return null;
	}
}
