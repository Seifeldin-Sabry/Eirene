package be.kdg.eirene.repository;

import be.kdg.eirene.model.Session;
import org.springframework.data.repository.CrudRepository;

public interface SessionRepository extends CrudRepository<Session, Long> {
	//	Session createSession(Session session);
	//
	//	List<Session> readSessions();
	//
	//	boolean deleteSession(Session session);

}
