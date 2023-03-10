package be.kdg.eirene.eirenespring;

import be.kdg.eirene.model.*;
import be.kdg.eirene.repository.Period;
import be.kdg.eirene.repository.SessionRepository;
import be.kdg.eirene.repository.UserRepository;
import be.kdg.eirene.service.SessionService;
import be.kdg.eirene.service.UserService;
import be.kdg.eirene.util.BcryptPasswordUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@SpringBootTest
class HibernateFunctionalityTest {

	private final UserRepository userRepository;
	private final SessionRepository sessionRepository;

	private final SessionService sessionService;

	private final UserService userService;
	private final Logger logger = Logger.getLogger(HibernateFunctionalityTest.class.getName());

	@Autowired
	public HibernateFunctionalityTest(UserRepository userRepository, SessionRepository sessionRepository, UserService userService, SessionService sessionService) {
		this.userRepository = userRepository;
		this.sessionRepository = sessionRepository;
		this.userService = userService;
		this.sessionService = sessionService;
	}


	@Test
	void userSaves() {
		User user = new User("Seifeldin Sabry", "is@gmail.com", "password", Gender.MALE);
		logger.info("User before saving: " + user);
		userRepository.save(user);
		logger.info("User after saving: " + user);
		User fromDb = userRepository.findById(user.getUser_id()).get();
		Assertions.assertNotNull(fromDb);
		Assertions.assertEquals("Seifeldin Sabry", fromDb.getName());
	}

	@Test
	void passwordSuccessfullyEncrypted() {
		User user = new User("Peter Buschenreiter", "p@hotmail.com", "verysecurepassword", Gender.MALE);
		//It throws an exception if the password is not encrypted (2nd parameter)
		Assertions.assertTrue(BcryptPasswordUtil.checkPassword("verysecurepassword", user.getPassword()));
	}

	@Test
	void sessionSavesAndSessionHistoryCorrectlyLoads() {
		User user = new User("Peter Buschenreiter", "p@hotmail.com", "verysecurepassword", Gender.MALE);
		Session session = new Session(SessionType.FOCUS);
		userRepository.save(user);
		session.setUser(user);
		user.setSession(session);
		session.stop();
		sessionRepository.save(session);
		Assertions.assertEquals(user, session.getUser());
		Assertions.assertEquals(session, user.getSession());
		Assertions.assertNotNull(user.getSession());
		Assertions.assertNotNull(session.getUser());
		logger.info("Session: " + session);
		logger.info("User: " + user);
		logger.info("Session history: " + user.getSessionHistory());
		User fromDb = userRepository.findById(user.getUser_id()).get();
		List<Session> sessionHistory = fromDb.getSessionHistory();
		logger.info("Session history from db: " + sessionHistory);
	}

	@Test
	void saveReadings() {
		User user = userRepository.findById(1L).get();
		Session session = new Session(SessionType.FOCUS, user);
		sessionRepository.save(session);
		logger.info("Session: " + session);
		Reading reading = new Reading(Timestamp.valueOf(LocalDateTime.now()), new BrainWaveReading(0, 100, 0), new SensorData(2, 2, 2, 2, 2));
		Reading reading2 = new Reading(Timestamp.valueOf(LocalDateTime.now()), new BrainWaveReading(0, 100, 0), new SensorData(2, 2, 2, 2, 2));
		session.addReading(reading);
		session.addReading(reading2);
		sessionRepository.save(session);
		Assertions.assertEquals(2, session.getReadings().size());
	}

	@Test
	void deleteUser() {
		Long sessionCount = sessionRepository.count();
		User user = userRepository.findById(1L).get();
		Long userSessionCount = sessionRepository.getSessionsCountByUserID(user.getUser_id());
		userRepository.delete(user);
		Assertions.assertFalse(userRepository.findById(1L).isPresent());
		Assertions.assertEquals(sessionCount - userSessionCount, sessionRepository.count());
	}

	@Test
	void groupDataTest() {
		User user = userRepository.findById(1L).get();
		List<Reading> readings = sessionService.getReadings(user.getUser_id(), Period.ALL, SessionType.FOCUS);
		readings.forEach(reading -> logger.info("Reading: " + reading));
	}

}
