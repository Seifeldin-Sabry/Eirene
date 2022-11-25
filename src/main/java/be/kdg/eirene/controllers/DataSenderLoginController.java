package be.kdg.eirene.controllers;

import be.kdg.eirene.exceptions.UnauthorizedAccessException;
import be.kdg.eirene.model.User;
import be.kdg.eirene.service.UserService;
import be.kdg.eirene.util.AEService;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/login")
public class DataSenderLoginController {

    private final AEService aes;
    private final UserService userService;
    private final Logger logger;

    @Autowired
    public DataSenderLoginController(UserService userService, AEService aes) {
        this.userService = userService;
        this.aes = aes;
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    @PostMapping
    public String login(@RequestHeader("email") String email, @RequestHeader("password") String password) {
        User retrievedUser = userService.getUser(email);
        if (retrievedUser == null || !userService.passwordMatches(retrievedUser, password)) {
            throw new UnauthorizedAccessException("Invalid email or password");
        }
        logger.info("user " + retrievedUser.getEmail() + " has connected with the DataSender");
        List<String> info = List.of(retrievedUser.getUser_id().toString(), aes.getSecret());
        return new GsonBuilder().create()
                .toJson(new ResponseEntity<>(info, HttpStatus.OK));
    }
}
