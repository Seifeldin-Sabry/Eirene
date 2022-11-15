package be.kdg.eirene.service;

import be.kdg.eirene.model.Session;
import be.kdg.eirene.model.SessionType;

import java.util.List;

public interface SessionService {
	List<Session> getSessions();

	Session getSession(Long id);

	Session addSession(SessionType type);
}
