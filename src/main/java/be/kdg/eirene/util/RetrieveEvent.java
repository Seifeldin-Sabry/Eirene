package be.kdg.eirene.util;

import be.kdg.eirene.model.Session;
import be.kdg.eirene.model.SessionHistory;
import be.kdg.eirene.model.User;
import org.hibernate.event.spi.PostLoadEvent;
import org.hibernate.event.spi.PostLoadEventListener;

import java.util.List;


public class RetrieveEvent implements PostLoadEventListener {

    public static final RetrieveEvent INSTANCE = new RetrieveEvent();

    /**
     * Called after loading an entity.
     * If the entity is a User, the sessionHistory is retrieved from the database and set.
     * @param event: the event that is fired
     */
    @Override
    public void onPostLoad(PostLoadEvent event) {
        final Object entity = event.getEntity();
        if (!(entity instanceof final User user)){
            return;
        }
        final List<Session> sessions = event.getSession()
                .createQuery("from Session where user_id = :id", Session.class)
                .setParameter("id", user.getId())
                .list();
        SessionHistory sessionHistory = new SessionHistory();
        sessionHistory.setSessions(sessions);
        user.setSessionHistory(sessionHistory);
    }
}
