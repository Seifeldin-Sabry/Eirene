package be.kdg.eirene.model;


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

    /**
     * Stops the session and sets the end time.
     */
    public void stop() {
        this.endTime = LocalDateTime.now();
    }
}
