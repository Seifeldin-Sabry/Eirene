package be.kdg.eirene.controllers;

import be.kdg.eirene.model.Session;
import be.kdg.eirene.presenter.viewmodel.SessionEditViewModel;
import be.kdg.eirene.service.CookieService;
import be.kdg.eirene.service.PaginationService;
import be.kdg.eirene.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping ("/profile/sessions")
public class SessionController {
	private final Logger logger;
	private final SessionService sessionService;
	private final CookieService cookieService;
	private final PaginationService paginationService;

	@Autowired
	public SessionController(SessionService sessionService, CookieService cookieService, PaginationService paginationService) {
		this.paginationService = paginationService;
		this.logger = LoggerFactory.getLogger(this.getClass());
		this.sessionService = sessionService;
		this.cookieService = cookieService;

	}

	@GetMapping ("/{page}")
	public ModelAndView loadSessions(HttpSession session, @PathVariable int page) {
		if (cookieService.cookieInvalid(session)) {
			return new ModelAndView("redirect:/");
		}
		if (paginationService.getNumberOfPages() == null) {
			paginationService.setNumberOfPages(sessionService.getSessionsPageCount(cookieService.getAttribute(session)));
		}
		paginationService.throwErrorIfPageDoesNotExist(page);
		paginationService.setPageNumber(page);
		int currentPage = paginationService.getPageNumber();
		return new ModelAndView("sessions")
				.addObject("sessions", sessionService.getSessions(cookieService.getAttribute(session), currentPage))
				.addObject("viewModel", new SessionEditViewModel())
				.addObject("page", currentPage)
				.addObject("numPages", paginationService.getNumberOfPages());
	}

	@GetMapping ("/session-overview/{sessionID}")
	public ModelAndView showSessionOverview(HttpSession httpSession, @PathVariable Long sessionID) {
		if (cookieService.cookieInvalid(httpSession)) {
			return new ModelAndView("redirect:/");
		}
		Session session = sessionService.getSession(sessionID, cookieService.getAttribute(httpSession));
		HashMap<String, List<Float>> sensorToData = sessionService.getReadingsBySensor(session);

		return new ModelAndView("session-overview")
				.addObject("sensorToData", sensorToData)
				.addObject("eireneSession", session);
	}

	@PutMapping ("/{id}")
	public ModelAndView editSession(@ModelAttribute ("viewModel") SessionEditViewModel viewModel, @PathVariable Long id, HttpSession httpSession) {
		final Long userId = cookieService.getAttribute(httpSession);
		if (cookieService.cookieInvalid(httpSession)) {
			return new ModelAndView("redirect:/");
		}
		final Session session = sessionService.getSession(id, userId);
		if (!viewModel.getSessionName().isBlank()) session.setName(viewModel.getSessionName());
		sessionService.updateSession(session);
		return new ModelAndView("redirect:/profile/sessions/" + (paginationService.getPageNumber()));
	}

	@DeleteMapping ("/{id}")
	public ModelAndView removeSession(HttpSession httpSession, @PathVariable Long id) {
		if (cookieService.cookieInvalid(httpSession)) {
			return new ModelAndView("redirect:/");
		}
		sessionService.deleteSession(sessionService.getSession(id, cookieService.getAttribute(httpSession)));
		paginationService.setNumberOfPages(sessionService.getSessionsPageCount(cookieService.getAttribute(httpSession)));
		return new ModelAndView("redirect:/profile/sessions/" + paginationService.getCorrectPageNumberAfterDelete());
	}
}
