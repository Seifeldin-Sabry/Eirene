package be.kdg.eirene.service;

import be.kdg.eirene.exceptions.UserNotFoundException;
import be.kdg.eirene.model.Gender;
import be.kdg.eirene.model.User;
import be.kdg.eirene.repository.UserRepository;
import be.kdg.eirene.util.BcryptPasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final Logger logger;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
		this.logger = LoggerFactory.getLogger(this.getClass());
	}

	@Override
	public User getUser(String email) {
		return userRepository.findByEmailIgnoreCase(email);
	}


	@Override
	public boolean passwordMatches(User user, String passwordToCheck) {
		return BcryptPasswordUtil.checkPassword(passwordToCheck, user.getPassword());
	}

	@Override
	public User getUser(Long id) {
		return userRepository.findById(id)
		                     .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
	}

	@Override
	public String redirectUnauthorized() {
		return "redirect:/login";
	}

	@Override
	public Long getTotalDuration(Long user_id) {
		Optional<Long> totalDurationSessionsByUserID = userRepository.getTotalDurationSessionsByUserID(user_id);
		logger.info("Total duration of sessions for user with id " + user_id + " is " + totalDurationSessionsByUserID);
		return totalDurationSessionsByUserID.orElse(0L);
	}

	@Override
	public Long getAverageDuration(Long user_id) {
		return userRepository.getAverageDurationSessionsByUserID(user_id).orElse(0L);
	}

	@Override
	public User addUser(String name, String email, String password, Gender gender) {
		return userRepository.save(new User(name, email, password, gender));
	}
}
