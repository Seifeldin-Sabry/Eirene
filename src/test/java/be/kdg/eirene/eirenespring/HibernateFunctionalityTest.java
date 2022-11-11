package be.kdg.eirene.eirenespring;

import be.kdg.eirene.model.Session;
import be.kdg.eirene.model.SessionType;
import be.kdg.eirene.util.HibernateUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HibernateFunctionalityTest {

    @Test
    void sessionSave() {
        try(org.hibernate.Session sessionDB = HibernateUtil.getSessionFactory().openSession()){
            sessionDB.beginTransaction();
            Session session = new Session(SessionType.FOCUS);
            sessionDB.save(session);
            sessionDB.getTransaction().commit();
            Assertions.assertNotNull(session.getId());
            Assertions.assertEquals(session, sessionDB.get(Session.class, session.getId()));
        }

    }

}
