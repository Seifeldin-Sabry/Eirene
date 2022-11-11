package be.kdg.eirene.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "sessions")
@NoArgsConstructor
@Setter
@Getter
public class Session {
	@Setter(AccessLevel.NONE)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "session_id", nullable = false)
	private Long id;

	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user_id;

	@Column(name = "start_time", nullable = false)
	private Timestamp startTime;

	@Column(name = "end_time", nullable = false)
	private Timestamp endTime;

	@Column(name = "session_type", nullable = false)
	private SessionType type;

	//TODO: fix Reading and SensorData
	@Transient
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "session_id")
	private List<Reading> readings;

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
