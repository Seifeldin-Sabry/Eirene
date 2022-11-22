package be.kdg.eirene.service;

import be.kdg.eirene.exceptions.SessionNotFoundException;
import be.kdg.eirene.model.Session;
import be.kdg.eirene.model.SessionType;
import be.kdg.eirene.repository.SessionRepository;
import com.google.common.collect.Lists;
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
	public List<Session> getSessions(Long userId) {
		return Lists.newArrayList(sessionRepository.getSessionsByUserID(userId));
	}

	@Override
	public Session getSession(Long id) {
		return sessionRepository.findById(id)
		                        .orElseThrow(() -> new SessionNotFoundException("Session with id " + id + " not found"));
	}

	@Override
	public Session addSession(SessionType type) {
		return sessionRepository.save(new Session(type));
	}
}
