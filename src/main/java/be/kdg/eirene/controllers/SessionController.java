package be.kdg.eirene.controllers;

import be.kdg.eirene.exceptions.PageNotFoundException;
import be.kdg.eirene.model.Session;
import be.kdg.eirene.presenter.viewmodel.SessionEditViewModel;
import be.kdg.eirene.service.CookieService;
import be.kdg.eirene.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping ("/profile/sessions")
public class SessionController {
	private final Logger logger;
	private final SessionService sessionService;
	private final CookieService cookieService;
	private Integer totalPages;
	private int currentPage;

	@Autowired
	public SessionController(SessionService sessionService, CookieService cookieService) {
		this.logger = LoggerFactory.getLogger(this.getClass());
		this.sessionService = sessionService;
		this.cookieService = cookieService;

	}

	@GetMapping ("/{page}")
	public ModelAndView loadSessions(HttpSession session, @PathVariable int page) {
		if (cookieService.cookieInvalid(session)) {
			return new ModelAndView("redirect:/");
		}
		// this is used to make only 1 call to the database for the total amount of sessions
		if (totalPages == null) {
			totalPages = sessionService.getSessionsPageCount(cookieService.getAttribute(session));
		}
		if (page <= 0 || page > totalPages) {
			throw new PageNotFoundException("Page not found");
		}
		currentPage = page - 1;
		logger.info("Total pages " + totalPages);
		logger.info("Loading sessions page " + currentPage);
		return new ModelAndView("sessions")
				.addObject("sessions", sessionService.getSessions(cookieService.getAttribute(session), currentPage))
				.addObject("viewModel", new SessionEditViewModel())
				.addObject("page", currentPage)
				.addObject("numPages", totalPages);
	}

	@GetMapping ("/session-overview/{sessionID}")
	public ModelAndView showSessionOverview(HttpSession httpSession, @PathVariable Long sessionID) {
		if (cookieService.cookieInvalid(httpSession)) {
			return new ModelAndView("redirect:/");
		}
		return new ModelAndView("session-overview")
				.addObject("eireneSession", sessionService.getSession(sessionID, cookieService.getAttribute(httpSession)));
	}

	@PutMapping ("/{id}")
	public ModelAndView editSession(@ModelAttribute ("viewModel") SessionEditViewModel viewModel, @PathVariable Long id, HttpSession httpSession) {
		final Long userId = cookieService.getAttribute(httpSession);
		if (cookieService.cookieInvalid(httpSession)) {
			return new ModelAndView("redirect:/");
		}
		final Session session = sessionService.getSession(id, userId);
		if (!"".equals(viewModel.getSessionName())) session.setName(viewModel.getSessionName());
		sessionService.updateSession(session);
		return new ModelAndView("redirect:/profile/sessions/" + (currentPage + 1));
	}

	@DeleteMapping ("/{id}")
	public ModelAndView removeSession(HttpSession httpSession, @PathVariable Long id) {
		if (cookieService.cookieInvalid(httpSession)) {
			return new ModelAndView("redirect:/");
		}
		sessionService.deleteSession(sessionService.getSession(id, cookieService.getAttribute(httpSession)));
		totalPages = sessionService.getSessionsPageCount(cookieService.getAttribute(httpSession));
		return new ModelAndView("redirect:/profile/sessions/" + (currentPage + 1 > totalPages ? totalPages : currentPage + 1));
	}
}
