package be.kdg.eirene.service;

import be.kdg.eirene.model.Session;
import be.kdg.eirene.model.SessionType;
import be.kdg.eirene.model.User;

import java.util.List;

public interface SessionService {
	List<Session> getSessions(Long userId);

	Session getSession(Long id, Long userId);

	Session save(SessionType type, User user);

	Long getSessionsCount(Long userId);

	void updateSession(Session session);

	void deleteSession(Session session);
}
