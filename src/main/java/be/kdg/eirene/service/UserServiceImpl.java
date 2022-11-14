package be.kdg.eirene.service;

import be.kdg.eirene.exceptions.UserNotFoundException;
import be.kdg.eirene.model.Sex;
import be.kdg.eirene.model.User;
import be.kdg.eirene.repository.UserRepository;
import com.google.common.collect.Lists;
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
		return Lists.newArrayList(userRepository.findAll());
	}

	@Override
	public User getUser(Long id) {
		return userRepository.findById(id)
		                     .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
	}

	@Override
	public User addUser(String name, String email, String password, Sex sex) {
		return userRepository.save(new User(name, email, password, sex));
	}
}
