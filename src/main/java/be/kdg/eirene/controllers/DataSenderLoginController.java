package be.kdg.eirene.controllers;

import be.kdg.eirene.exceptions.UnauthorizedAccessException;
import be.kdg.eirene.model.User;
import be.kdg.eirene.service.UserService;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api/login")
public class DataSenderLoginController {

	private final UserService userService;

	@Autowired
	public DataSenderLoginController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public String login(@RequestHeader ("email") String email, @RequestHeader ("password") String password) {
		User retrievedUser = userService.getUser(email);
		if (retrievedUser == null || !userService.passwordMatches(retrievedUser, password)) {
			throw new UnauthorizedAccessException("Invalid email or password");
		}
		return new GsonBuilder().create()
		                        .toJson(new ResponseEntity<>(userService.getUser(email).getUser_id(), HttpStatus.OK));
	}
}
