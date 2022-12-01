package be.kdg.eirene.repository;

import be.kdg.eirene.model.Reading;
import be.kdg.eirene.model.Session;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Transactional
public interface SessionRepository extends CrudRepository<Session, Long> {
	@Query (value = "SELECT * FROM readings " +
			"JOIN sessions USING (session_id) " +
			"WHERE session_id = ?1", nativeQuery = true)
	List<Reading> getReadingsBySessionID(Long id);

	@Query ("select s from Session s where s.user.user_id = ?1 AND s.id = ?2")
	Optional<Session> getSessionWhereUserID(Long userId, Long sessionId);


	@Query ("select s from Session s where s.user.user_id = ?1  AND s.endTime IS NOT NULL")
	List<Session> getSessionsByUserID(Long id);

	@Query ("select COUNT(s) from Session s where s.user.user_id = ?1  AND s.endTime IS NOT NULL")
	Long getSessionsCountByUserID(Long id);

	@org.springframework.transaction.annotation.Transactional
	@Modifying
	@Query ("update Session s set s.endTime = ?1, s.satisfaction = ?2, s.name = ?3 where s.id = ?4")
	void updateSession(@NonNull Timestamp endTime, @Nullable Integer satisfaction, @Nullable String name, Long id);

}
