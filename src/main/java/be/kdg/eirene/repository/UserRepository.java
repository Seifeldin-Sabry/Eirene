package be.kdg.eirene.repository;

import be.kdg.eirene.model.User;

import java.util.List;

public interface UserRepository {
	User createUser(User user);

	List<User> readUsers();

	boolean deleteUser(User user);
}
