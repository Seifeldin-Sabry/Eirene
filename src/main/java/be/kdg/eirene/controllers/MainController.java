package be.kdg.eirene.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

	@GetMapping ("/")
	public ModelAndView index(HttpSession session) {
		boolean loggedIn = session.getAttribute("user_id") != null;
		return new ModelAndView("index").addObject("loggedIn", loggedIn);
	}
}
