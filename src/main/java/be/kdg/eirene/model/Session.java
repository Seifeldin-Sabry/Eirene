package main.java.be.kdg.eirene.model;


import java.time.LocalDateTime;


public class Session {
    private final LocalDateTime startTime;
    private final boolean isFocus;
    private long id;
    private LocalDateTime endTime;

    public Session(boolean isFocus) {
        this.startTime = LocalDateTime.now();
        this.isFocus = isFocus;
    }
}
