package be.kdg.eirene.repository;

import be.kdg.eirene.model.Session;

import java.util.List;

public interface SessionRepository {
	Session createSession(Session session);

	List<Session> readSessions();

	boolean deleteSession(Session session);
}
