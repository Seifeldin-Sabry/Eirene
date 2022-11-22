package be.kdg.eirene.service;

import be.kdg.eirene.model.Gender;
import be.kdg.eirene.model.User;

import java.util.List;

public interface UserService {
	List<User> getUsers();

	User getUser(String email);

	boolean passwordMatches(User user, String passwordToCheck);

	User getUser(Long id);

	User addUser(String name, String email, String password, Gender gender);

	String redirectUnauthorized();

	Long getTotalDuration(Long user_id);

	Long getAverageDuration(Long user_id);
}
