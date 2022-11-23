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

	@Query ("select s from Session s where s.user.user_id = ?1")
	List<Session> getSessionsByUserID(Long id);

	//
	//	List<Session> readSessions();
	//
	//	boolean deleteSession(Session session);

}
