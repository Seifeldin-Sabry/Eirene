//package be.kdg.eirene.repository;
//
//import be.kdg.eirene.model.Session;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class SessionRepositoryImpl implements SessionRepository {
//	private final List<Session> sessions;
//
//	@Autowired
//	public SessionRepositoryImpl() {
//		this.sessions = new ArrayList<>();
//	}
//
//	@Override
//	public Session createSession(Session session) {
//		sessions.add(session);
//		return session;
//	}
//
//	@Override
//	public List<Session> readSessions() {
//		return sessions;
//	}
//
//	@Override
//	public boolean deleteSession(Session session) {
//		return sessions.remove(session);
//	}
//}
