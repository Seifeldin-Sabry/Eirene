package be.kdg.eirene.service;

import be.kdg.eirene.model.Session;

import java.util.List;

public interface SessionService {
	List<Session> getSessions(Long userId);

	Session getSession(Long id, Long userId);

	Session save(Session session);

	Long getSessionsCount(Long userId);

	void updateSession(Session session);

	void deleteSession(Session session);
}
