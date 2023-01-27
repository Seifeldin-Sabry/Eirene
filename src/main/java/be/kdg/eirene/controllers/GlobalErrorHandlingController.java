package be.kdg.eirene.controllers;

import be.kdg.eirene.exceptions.PageNotFoundException;
import be.kdg.eirene.exceptions.SessionNotFoundException;
import be.kdg.eirene.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalErrorHandlingController {

	@ResponseStatus (HttpStatus.NOT_FOUND)
	@ExceptionHandler ({PageNotFoundException.class, SessionNotFoundException.class, UserNotFoundException.class})
	ModelAndView handleEntityNotFoundException(Exception e) {
		return new ModelAndView("error/404")
				.addObject("message", e.getMessage());
	}
}
