package be.kdg.eirene.eirenespring;

import be.kdg.eirene.model.Session;
import be.kdg.eirene.model.SessionType;
import be.kdg.eirene.model.Sex;
import be.kdg.eirene.model.User;
import be.kdg.eirene.repository.SessionRepository;
import be.kdg.eirene.repository.UserRepository;
import be.kdg.eirene.util.BcryptPasswordUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.logging.Logger;

@SpringBootTest
class HibernateFunctionalityTest {

	private final UserRepository userRepository;
	private final SessionRepository sessionRepository;
	private final Logger logger = Logger.getLogger(HibernateFunctionalityTest.class.getName());

	@Autowired
	public HibernateFunctionalityTest(UserRepository userRepository, SessionRepository sessionRepository) {
		this.userRepository = userRepository;
		this.sessionRepository = sessionRepository;
	}


	@Test
	void userSaves() {
		User user = new User("Seifeldin Sabry", "is@gmail.com", "password", Sex.MALE);
		logger.info("User before saving: " + user);
		userRepository.save(user);
		logger.info("User after saving: " + user);
		User fromDb = userRepository.findById(user.getUser_id()).get();
		Assertions.assertNotNull(fromDb);
		Assertions.assertEquals("Seifeldin Sabry", fromDb.getName());
	}

	@Test
	void passwordSuccessfullyEncrypted() {
		User user = new User("Peter Buschenreiter", "p@hotmail.com", "verysecurepassword", Sex.MALE);
		//It throws an exception if the password is not encrypted (2nd parameter)
		Assertions.assertTrue(BcryptPasswordUtil.checkPassword("verysecurepassword", user.getPassword()));
	}

	@Test
	void sessionSavesAndSessionHistoryCorrectlyLoads() {
		User user = new User("Peter Buschenreiter", "p@hotmail.com", "verysecurepassword", Sex.MALE);
		Session session = new Session(SessionType.FOCUS);
		userRepository.save(user);
		session.setUser(user);
		user.setSession(session);
		session.stop();
		sessionRepository.save(session);
		Assertions.assertEquals(1, sessionRepository.count());
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
		Assertions.assertEquals(1, fromDb.getSessionHistory().size());
	}

}
