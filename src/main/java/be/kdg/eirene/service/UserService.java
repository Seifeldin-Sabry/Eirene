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

	void updateProfile(Long userId, String name, String email, Gender gender);

	void updatePassword(Long userId, String password);

	boolean newEmailIsCurrentEmail(User user, String email);

	boolean emailExists(String email);

	void deleteUser(Long userId);
}
