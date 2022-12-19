package be.kdg.eirene.service;

import be.kdg.eirene.exceptions.SessionNotFoundException;
import be.kdg.eirene.model.Reading;
import be.kdg.eirene.model.Session;
import be.kdg.eirene.model.SessionType;
import be.kdg.eirene.repository.GlobalAnalCategory;
import be.kdg.eirene.repository.Period;
import be.kdg.eirene.repository.SessionRepository;
import be.kdg.eirene.util.ReadingsAdapt;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static be.kdg.eirene.model.Sensor.*;
import static org.slf4j.LoggerFactory.getLogger;

@Service
public class SessionServiceImpl implements SessionService {
	private final SessionRepository sessionRepository;

	private final ReadingsAdapt readingsAdaptor;

	private final PaginationService paginationService;

	private final GlobalAnalyticsComparator globalAnalyticsComparator;
	private final Logger logger = getLogger(this.getClass());

	@Autowired
	public SessionServiceImpl(SessionRepository sessionRepository, ReadingsAdapt readingsAdaptor, PaginationService paginationService, GlobalAnalyticsComparator globalAnalyticsComparator) {
		this.sessionRepository = sessionRepository;
		this.readingsAdaptor = readingsAdaptor;
		this.paginationService = paginationService;
		this.globalAnalyticsComparator = globalAnalyticsComparator;
	}


	@Override
	public Integer getSessionsPageCount(Long userId) {
		return sessionRepository.getSessionsByUserID(userId, Pageable.ofSize(paginationService.getPageSize()))
		                        .getTotalPages();
	}

	@Override
	public List<Session> getSessions(Long userId, int page) {
		Pageable pageSession = PageRequest.of(page - 1, paginationService.getPageSize());
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
		return readingsAdaptor.convert(sessionRepository.getReadingsByUserID(userId, period.name()
		                                                                                   .toLowerCase(), sessionType.name()));
	}

	@Override
	public HashMap<String, List<Float>> getReadingsBySensor(Session session) {
		HashMap<String, List<Float>> readings = new HashMap<>();
		session.getReadings().forEach(reading -> {
			readings.computeIfAbsent(HR.name(), k -> new ArrayList<>());
			readings.computeIfAbsent(TEMPERATURE.name(), k -> new ArrayList<>());
			readings.computeIfAbsent(SOUND.name(), k -> new ArrayList<>());
			readings.computeIfAbsent(PHOTOTRANSISTOR.name(), k -> new ArrayList<>());
			readings.computeIfAbsent(HUMIDITY.name(), k -> new ArrayList<>());
			readings.computeIfAbsent(BRAINWAVE.name(), k -> new ArrayList<>());

			readings.get(HR.name()).add(reading.getSensorData().getHeartRate());
			readings.get(TEMPERATURE.name()).add(reading.getSensorData().getTemperature());
			readings.get(SOUND.name()).add(reading.getSensorData().getSound());
			readings.get(PHOTOTRANSISTOR.name()).add(reading.getSensorData().getLight());
			readings.get(HUMIDITY.name()).add(reading.getSensorData().getHumidity());
			readings.get(BRAINWAVE.name()).add((float) reading.getBrainWave().getLevel(session.getType()));
		});
		return readings;
	}

	@Override
	public String getUserGlobalAverageComparison(Long userId, SessionType sessionType) {
		Double averageBrainUser = sessionRepository.getAverageBrainwaveStrengthByUserID(userId, sessionType.name());
		Double averageBrainGlobal = sessionRepository.getAverageBrainwaveStrengthByOtherUsers(userId, sessionType.name());
		if (averageBrainUser == null || averageBrainGlobal == null) {
			return "";
		}
		final int percentile = Math.round((float) (averageBrainUser / averageBrainGlobal) * 100);
		GlobalAnalCategory globalAverageComparison = globalAnalyticsComparator.getGlobalAverageComparison(averageBrainUser, averageBrainGlobal);
		return String.format("Your average %s is in the top %d%% percentile, that's %s", sessionType.name()
		                                                                                            .toLowerCase(), percentile, globalAverageComparison.getCapitalizedName());
	}

}
