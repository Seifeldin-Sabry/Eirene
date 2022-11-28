package be.kdg.eirene.controllers;

import be.kdg.eirene.model.Reading;
import be.kdg.eirene.model.Session;
import be.kdg.eirene.model.SessionType;
import be.kdg.eirene.model.User;
import be.kdg.eirene.presenter.viewmodel.SessionFeedbackViewModel;
import be.kdg.eirene.service.CookieService;
import be.kdg.eirene.service.EvaluatorService;
import be.kdg.eirene.service.SessionService;
import be.kdg.eirene.service.UserService;
import be.kdg.eirene.util.RequestDecryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping ("/newsession")
public class ActiveSessionController {

	private final CookieService cookieService;
	private final SessionService sessionService;
	private final UserService userService;
	private final EvaluatorService evaluatorService;
	private final Logger logger;
	private final RequestDecryptor decryptor;
	private Session session;

	@Autowired
	public ActiveSessionController(RequestDecryptor decryptor, CookieService cookieService, UserService userService, SessionService sessionService, EvaluatorService evaluatorService) {
		this.decryptor = decryptor;
		this.cookieService = cookieService;
		this.userService = userService;
		this.sessionService = sessionService;
		this.evaluatorService = evaluatorService;
		logger = LoggerFactory.getLogger(this.getClass());
	}

	@GetMapping
	public ModelAndView showNewSession(@RequestParam SessionType type, HttpSession httpSession) {
		if (cookieService.cookieInvalid(httpSession)) {
			return new ModelAndView("redirect:/");
		}
		if (session == null) {
			User user = userService.getUser(cookieService.getAttribute(httpSession));
			session = sessionService.save(type, user);
			session.setUser(user);
		}
		logger.info(" report: " + evaluatorService.formulateReport(session.getReadings(), type));
		return new ModelAndView("active-session").addObject("type", StringUtils.capitalize(type
				                                         .toString()
				                                         .toLowerCase()))
		                                         .addObject("session", session)
		                                         .addObject("report", evaluatorService.formulateReport(session.getReadings(), type));
	}

	@PostMapping
	public ModelAndView getData(@RequestBody Reading data) {
		if (session == null) {
			return null;
		}
		session.addReading(data);
		logger.info("Post called");
		return new ModelAndView("active-session").addObject("type", StringUtils.capitalize(session.getType()
		                                                                                          .toString()
		                                                                                          .toLowerCase()))
		                                         .addObject("report", evaluatorService.formulateReport(session.getReadings(), session.getType()));
	}

	@GetMapping ("/stopsession")
	public ModelAndView stopSession() {
		if (session == null) {
			return new ModelAndView("redirect:/profile");
		}
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
		Long sessionId = session.getId();
		session = null;
		return new ModelAndView("redirect:/profile/sessions/session-overview/" + sessionId);
	}

	@GetMapping ("/discard-session")
	public ModelAndView discardSession(HttpSession httpSession) {
		if (cookieService.cookieInvalid(httpSession)) {
			return new ModelAndView("redirect:/");
		}
		sessionService.deleteSession(session);
		session = null;
		return new ModelAndView("redirect:/profile");
	}
}
