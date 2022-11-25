package be.kdg.eirene.controllers;

import be.kdg.eirene.model.Reading;
import be.kdg.eirene.model.Session;
import be.kdg.eirene.model.SessionType;
import be.kdg.eirene.model.User;
import be.kdg.eirene.service.CookieService;
import be.kdg.eirene.service.SessionService;
import be.kdg.eirene.service.UserService;
import be.kdg.eirene.util.RequestDecryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/newsession")
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
		User user = userService.getUser(cookieService.getAttribute(httpSession));
		session = sessionService.save(type, user);
		user.setSession(session);
		logger.info(session.toString());
		return new ModelAndView("active-session").addObject("type", type)
		                                         .addObject("session", session);
	}

    @PostMapping
    public void getData(@RequestBody Reading data) {
        logger.info("Data received: " + data);
    }
}
