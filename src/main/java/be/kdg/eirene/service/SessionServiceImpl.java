package be.kdg.eirene.service;

import be.kdg.eirene.exceptions.SessionNotFoundException;
import be.kdg.eirene.model.Reading;
import be.kdg.eirene.model.Session;
import be.kdg.eirene.model.SessionType;
import be.kdg.eirene.repository.Period;
import be.kdg.eirene.repository.SessionRepository;
import be.kdg.eirene.util.ReadingsAdapt;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {
	private final SessionRepository sessionRepository;

	private final ReadingsAdapt readingsAdaptor;

	private final int PAGE_SIZE = 10;

	@Autowired
	public SessionServiceImpl(SessionRepository sessionRepository, ReadingsAdapt readingsAdaptor) {
		this.sessionRepository = sessionRepository;
		this.readingsAdaptor = readingsAdaptor;
	}


	@Override
	public Integer getSessionsPageCount(Long userId) {
		return sessionRepository.getSessionsByUserID(userId, Pageable.ofSize(PAGE_SIZE)).getTotalPages();
	}

	@Override
	public List<Session> getSessions(Long userId, int page) {
		Pageable pageSession = PageRequest.of(page, PAGE_SIZE);
		return Lists.newArrayList(sessionRepository.getSessionsByUserID(userId, pageSession));
	}

	@Override
	public Session getSession(Long id, Long userId) {
		return sessionRepository.getSessionWhereUserID(userId, id)
		                        .orElseThrow(() -> new SessionNotFoundException("Session not found"));
	}

	@Override
	public Session save(Session session) {
		return sessionRepository.save(session);
	}

	@Override
	public Long getSessionsCount(Long userId) {
		return sessionRepository.getSessionsCountByUserID(userId);
	}

	@Override
	public void updateSession(Session session) {
		if (session.getName().isBlank() || session.getName().isEmpty()) {
			session.setDefaultName();
		}
		sessionRepository.save(session);
	}

	@Override
	public void deleteSession(Session session) {
		sessionRepository.delete(session);
	}

	@Override
	public List<Reading> getReadings(Long userId, Period period, SessionType sessionType) {
		return readingsAdaptor.convert(sessionRepository.getReadingsByUserID(userId, period.name(), sessionType.name()));
	}

}
