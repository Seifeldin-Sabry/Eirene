package main.java.be.kdg.eirene.model;

public class User {
    private final String name;
    private final String email;
    private final String password;
    private final SessionHistory sessionHistory;
    private final Session session;
    private long id;

    public User(String name, String email, String password, SessionHistory sessionHistory, Session session) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.sessionHistory = sessionHistory;
        this.session = session;
    }
}
