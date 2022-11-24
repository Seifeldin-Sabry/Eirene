package be.kdg.eirene.service;

import be.kdg.eirene.model.Gender;
import be.kdg.eirene.model.User;

public interface UserService {
	User getUser(String email);

	boolean passwordMatches(User user, String passwordToCheck);

	User getUser(Long id);

	User addUser(String name, String email, String password, Gender gender);

	Long getTotalDuration(Long user_id);

	Long getAverageDuration(Long user_id);
}
