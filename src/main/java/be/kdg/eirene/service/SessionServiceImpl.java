package be.kdg.eirene.service;

import be.kdg.eirene.model.Session;
import be.kdg.eirene.model.SessionType;
import be.kdg.eirene.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {
	private final SessionRepository sessionRepository;

	@Autowired
	public SessionServiceImpl(SessionRepository sessionRepository) {
		this.sessionRepository = sessionRepository;
	}

	@Override
	public List<Session> getSessions() {
		return sessionRepository.readSessions();
	}

	@Override
	@Deprecated
	public Session getSession(long id) {
		return getSessions().get(0);
	}

	@Override
	public Session addSession(SessionType type) {
		return sessionRepository.createSession(new Session(type));
	}
}
