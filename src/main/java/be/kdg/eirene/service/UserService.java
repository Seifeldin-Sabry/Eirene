package be.kdg.eirene.service;

import be.kdg.eirene.model.Gender;
import be.kdg.eirene.model.User;

import java.util.List;

public interface UserService {
	List<User> getUsers();

	User getUser(String email);

	User addUser(String name, String email, String password, Gender gender);
}
