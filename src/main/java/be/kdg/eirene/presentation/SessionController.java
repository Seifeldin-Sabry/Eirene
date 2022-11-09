package be.kdg.eirene.presentation;

import be.kdg.eirene.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SessionController {
	private final Logger logger;
	private final SessionService sessionService;

	@Autowired
	public SessionController(SessionService sessionService) {
		this.logger = LoggerFactory.getLogger(this.getClass());
		this.sessionService = sessionService;
	}
}
