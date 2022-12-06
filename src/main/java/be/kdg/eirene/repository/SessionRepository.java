package be.kdg.eirene.repository;

import be.kdg.eirene.model.Session;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface SessionRepository extends CrudRepository<Session, Long> {
	@Query ("select s from Session s where s.user.user_id = ?1 AND s.id = ?2")
	Optional<Session> getSessionWhereUserID(Long userId, Long sessionId);


	@Query ("select s from Session s where s.user.user_id = ?1  AND s.endTime IS NOT NULL order by s.startTime, s.id desc")
	List<Session> getSessionsByUserID(Long id);

	@Query ("select COUNT(s) from Session s where s.user.user_id = ?1  AND s.endTime IS NOT NULL")
	Long getSessionsCountByUserID(Long id);

	@Query (value = """
			SELECT r.time_stamp, sd.heart_rate, sd.humidity, sd.light, sd.sound, sd.temperature, b.focus, b.meditation, b.signal
			FROM readings r
			JOIN brainwaves b USING(brainwave_id)
			JOIN sensor_data sd USING(sensor_data_id)
			JOIN sessions s USING (session_id)
			JOIN users u USING (user_id)
			WHERE u.user_id = ?1
			AND CASE
			    WHEN ?2 = 'day' THEN
			        DATE_PART('day', date(r.time_stamp)) = DATE_PART('day', date(NOW()))
			        AND DATE_PART('week', date(r.time_stamp)) = DATE_PART('week', date(NOW()))
			        AND DATE_PART('month', date(r.time_stamp)) = DATE_PART('month', date(NOW()))
			        AND DATE_PART('year', date(r.time_stamp)) = DATE_PART('year', date(NOW()))
			    WHEN ?2  = 'week' THEN
			        DATE_PART('week', date(r.time_stamp)) = DATE_PART('week', date(NOW()))
			        AND DATE_PART('month', date(r.time_stamp)) = DATE_PART('month', date(NOW()))
			        AND DATE_PART('year', date(r.time_stamp)) = DATE_PART('year', date(NOW()))
			    WHEN ?2  = 'month' THEN
			        DATE_PART('month', date(r.time_stamp)) = DATE_PART('month', date(NOW()))
			        AND DATE_PART('year', date(r.time_stamp)) = DATE_PART('year', date(NOW()))
			    WHEN ?2  = 'year' THEN DATE_PART('year', date(r.time_stamp)) = DATE_PART('year', date(NOW()))
			    ELSE TRUE
			    END
			AND CASE WHEN UPPER(?3) = 'FOCUS' THEN s.session_type = 'FOCUS'
			    WHEN UPPER(?3) = 'MEDITATION' THEN s.session_type = 'MEDITATION'
			    ELSE TRUE
			    END
			ORDER BY r.time_stamp
			;""", nativeQuery = true)
	List<Object[]> getReadingsByUserID(Long id, String period, String sessionType);

	@Query (value = """
			SELECT r.time_stamp, sd.heart_rate, sd.humidity, sd.light, sd.sound, sd.temperature ,b.focus, b.meditation, b.signal
			FROM readings r
			JOIN brainwaves b USING(brainwave_id)
			JOIN sensor_data sd USING(sensor_data_id)
			JOIN sessions s USING (session_id)
			JOIN users u USING (user_id)
			WHERE u.user_id = ?1
			AND CASE WHEN UPPER(?3) = 'FOCUS' THEN s.session_type = 'FOCUS'
			    WHEN UPPER(?3) = 'MEDITATION' THEN s.session_type = 'MEDITATION'
			    ELSE TRUE
			    END
			ORDER BY r.time_stamp;
			""", nativeQuery = true)
	List<Object[]> getReadingsByUserID(Long id, String sessionType);
}
