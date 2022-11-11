package be.kdg.eirene.util;


import be.kdg.eirene.model.Session;
import be.kdg.eirene.model.User;
import be.kdg.eirene.model.BrainWaveReading;
import be.kdg.eirene.model.Reading;
import be.kdg.eirene.model.SensorReading;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration()
                    .configure()
                    .addAnnotatedClass(Session.class)
                    .addAnnotatedClass(Reading.class)
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(BrainWaveReading.class)
                    .addAnnotatedClass(SensorReading.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
