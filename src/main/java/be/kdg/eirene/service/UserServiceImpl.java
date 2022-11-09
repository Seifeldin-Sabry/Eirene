package be.kdg.eirene.service;

import be.kdg.eirene.model.Sex;
import be.kdg.eirene.model.User;
import be.kdg.eirene.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> getUsers() {
		return userRepository.readUsers();
	}

	@Override
	@Deprecated
	public User getUser(long id) {
		return getUsers().get(0);
	}

	@Override
	public User addUser(String name, String email, String password, Sex sex) {
		return userRepository.createUser(new User(name, email, password, sex));
	}
}
