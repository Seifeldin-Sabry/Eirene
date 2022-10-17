package be.kdg.eirene.model;

import lombok.Getter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
public class Session {
	private final Timestamp startTime;
	private final SessionType type;
	private final long id;
	private final List<Reading> readings;
	private Timestamp endTime;

	public Session(SessionType type) {
		this.startTime = new Timestamp(System.currentTimeMillis());
		this.type = type;
		id = new Random().nextLong(); // will be changed in the future
		readings = new ArrayList<>();
	}

	/**
	 Stops the session and sets the end time.
	 */
	public void stop() {
		this.endTime = new Timestamp(System.currentTimeMillis());
	}
}
