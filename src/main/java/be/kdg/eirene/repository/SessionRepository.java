package be.kdg.eirene.repository;

import be.kdg.eirene.model.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface SessionRepository extends CrudRepository<Session, Long> {
//	Session createSession(Session session);
//
//	List<Session> readSessions();
//
//	boolean deleteSession(Session session);

}
