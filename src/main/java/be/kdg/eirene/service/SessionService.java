package be.kdg.eirene.service;

import be.kdg.eirene.model.Session;
import be.kdg.eirene.model.SessionType;
import be.kdg.eirene.model.User;

import java.util.List;

public interface SessionService {
	List<Session> getSessions(Long userId);

	Session getSession(Long id);

	Session save(SessionType type, User user);

	Long getSessionsCount(Long userId);
}
