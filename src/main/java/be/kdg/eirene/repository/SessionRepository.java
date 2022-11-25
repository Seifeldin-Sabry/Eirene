package be.kdg.eirene.repository;

import be.kdg.eirene.model.Reading;
import be.kdg.eirene.model.Session;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface SessionRepository extends CrudRepository<Session, Long> {
	@Query ("select s from Session s where s.id = ?1")
	List<Reading> getReadingsBySessionID(Long id);

	@Query ("select s from Session s where s.user.user_id = ?1  AND s.endTime IS NOT NULL")
	List<Session> getSessionsByUserID(Long id);

	@Query ("select COUNT(s) from Session s where s.user.user_id = ?1  AND s.endTime IS NOT NULL")
	Long getSessionsCountByUserID(Long id);

	//
	//	List<Session> readSessions();
	//
	//	boolean deleteSession(Session session);

}
