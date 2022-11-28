package be.kdg.eirene.service;

import be.kdg.eirene.exceptions.SessionNotFoundException;
import be.kdg.eirene.model.Session;
import be.kdg.eirene.model.SessionType;
import be.kdg.eirene.model.User;
import be.kdg.eirene.repository.SessionRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
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
	public Session save(SessionType type, User user) {
		return sessionRepository.save(new Session(type, user));
	}

	@Override
	public Long getSessionsCount(Long userId) {
		return sessionRepository.getSessionsCountByUserID(userId);
	}

	@Override
	public void updateSession(Session session) {
		if (session.getName().isBlank() || session.getName().isEmpty()) {
			session.setName(session.getStartTime()
			                       .toLocalDateTime()
			                       .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
		}
		sessionRepository.updateSession(session.getEndTime(), session.getSatisfaction(), session.getName(), session.getId());
	}

	@Override
	public void deleteSession(Session session) {
		sessionRepository.delete(session);
	}
}
