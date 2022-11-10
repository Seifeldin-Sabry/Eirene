package be.kdg.eirene.model;

import be.kdg.eirene.model.readers.Reading;
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

	/**
	 Calculates and returns the total duration of the session.

	 @return the total duration of the session
	 */
	public long getDuration() {
		return this.endTime.getTime() - this.startTime.getTime();
	}

	/**
	 Calculates and returns the duration of the session from the start until now.

	 @return the duration of the session until now
	 */
	public long getCurrentDuration() {
		return System.currentTimeMillis() - this.startTime.getTime();
	}
}
