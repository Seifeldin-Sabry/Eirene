package be.kdg.eirene.service;

import be.kdg.eirene.model.Reading;
import be.kdg.eirene.model.Session;
import be.kdg.eirene.model.SessionType;
import be.kdg.eirene.repository.Period;

import java.util.HashMap;
import java.util.List;

public interface SessionService {
	Integer getSessionsPageCount(Long userId);

	List<Session> getSessions(Long userId, int page);

	Session getSession(Long id, Long userId);

	Session save(Session session);

	Long getSessionsCount(Long userId);

	void deleteSession(Session session);

	List<Reading> getReadings(Long userId, Period period, SessionType sessionType);

	HashMap<String, List<Float>> getReadingsBySensor(Session session);

	String getUserGlobalAverageComparison(Long userId, SessionType sessionType);
}
