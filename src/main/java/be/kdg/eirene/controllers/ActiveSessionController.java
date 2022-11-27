package be.kdg.eirene.controllers;

import be.kdg.eirene.model.Reading;
import be.kdg.eirene.model.Session;
import be.kdg.eirene.model.SessionType;
import be.kdg.eirene.model.User;
import be.kdg.eirene.presenter.viewmodel.SessionFeedbackViewModel;
import be.kdg.eirene.service.CookieService;
import be.kdg.eirene.service.SessionService;
import be.kdg.eirene.service.UserService;
import be.kdg.eirene.util.RequestDecryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
		Boolean sessionActive = cookieService.isSessionActive(httpSession);
		if (sessionActive == null || !sessionActive)
			return new ModelAndView("redirect:/profile");
		//TODO: check for null because of order of action by the datasender
		session.addReading(data);
		logger.info(session.getReadings().toString());
		logger.info("Data received: " + data);
		return new ModelAndView("active-session").addObject("type", session.getType());
	}

	@GetMapping ("/stopsession")
	public ModelAndView stopSession(HttpSession httpSession) {
		Boolean sessionActive = cookieService.isSessionActive(httpSession);
		if (sessionActive == null || !sessionActive)
			return new ModelAndView("redirect:/profile");
		session.stop();
		return new ModelAndView("feedback").addObject("sessionFeedback", new SessionFeedbackViewModel());
	}

	@PostMapping ("/submit-feedback")
	public ModelAndView submit(@ModelAttribute ("sessionFeedback") @Valid SessionFeedbackViewModel sessionFeedbackViewModel, BindingResult errors, HttpSession httpSession) {
		if (cookieService.cookieInvalid(httpSession)) {
			return new ModelAndView("redirect:/");
		}
		if (errors.hasErrors()) {
			return new ModelAndView("feedback");
		}
		logger.info("request radio " + sessionFeedbackViewModel.getSatisfactionLevel());
		logger.info(sessionFeedbackViewModel.getSessionName());
		session.setSatisfaction(sessionFeedbackViewModel.getSatisfactionLevel().getValue());
		session.setName(sessionFeedbackViewModel.getSessionName());
		sessionService.updateSession(session);
		return new ModelAndView("redirect:/session-overview");
	}

	@GetMapping ("/discard-session")
	public ModelAndView discardSession(HttpSession httpSession) {
		if (cookieService.cookieInvalid(httpSession)) {
			return new ModelAndView("redirect:/");
		}
		Boolean sessionActive = cookieService.isSessionActive(httpSession);
		if (sessionActive)
			cookieService.setSessionCookie(httpSession, Boolean.FALSE);
		sessionService.deleteSession(session);
		return new ModelAndView("redirect:/profile");
	}

	@InitBinder
	void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(false);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
}
