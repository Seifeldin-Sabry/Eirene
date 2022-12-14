package be.kdg.eirene.model;

import be.kdg.eirene.util.PostgreSQLEnumType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.ocpsoft.prettytime.PrettyTime;
import org.slf4j.Logger;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table (name = "sessions")
@TypeDef (name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Session {
	@Transient
	Logger logger = org.slf4j.LoggerFactory.getLogger(Session.class);
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "session_id", nullable = false)
	private Long id;
	@Column (name = "session_name")
	private String name;
	@Column (name = "satisfaction")
	private Integer satisfaction;
	@Column (name = "start_time", nullable = false)
	private Timestamp startTime;
	@Column (name = "end_time")
	private Timestamp endTime;
	@Enumerated (EnumType.STRING)
	@Column (name = "session_type", nullable = false)
	@Type (type = "pgsql_enum")
	private SessionType type;
	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn (name = "session_id", nullable = false)
	@ToString.Exclude
	private List<Reading> readings;
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "user_id", nullable = false)
	@ToString.Exclude
	private User user;

	public Session(SessionType type) {
		this.startTime = new Timestamp(System.currentTimeMillis());
		this.type = type;
		readings = new ArrayList<>();
	}

	public Session(SessionType type, User user) {
		this(type);
		this.user = user;
	}

	/**
	 sets a default name for the session
	 */
	public void setDefaultName() {
		this.name = startTime.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}

	/**
	 Stops the session and sets the end time
	 */
	public void stop() {
		this.endTime = new Timestamp(System.currentTimeMillis());
	}

	/**
	 Calculates the duration of the session in seconds.

	 @return The duration of the session in seconds.
	 */
	public Duration getDuration() {
		return Duration.of(this.endTime.getTime() - this.startTime.getTime(), ChronoUnit.MILLIS);
	}

	/**
	 Calculates and returns the duration of the session from the start until time of invocation.

	 @return the duration of the session from the start until time of invocation.
	 */
	public long getCurrentDuration() {
		return Duration.of(System.currentTimeMillis() - this.startTime.getTime(), ChronoUnit.MILLIS).toSeconds();
	}

	public String getPrettyTime() {
		PrettyTime prettyTime = new PrettyTime();
		return prettyTime.format(startTime);
	}

	public String getPrettyDuration() {
		return getDuration().toString().substring(2).replaceAll("(\\d[HMS])(?!$)", "$1 ").toLowerCase();
	}

	public boolean addReading(Reading reading) {
		return readings.add(reading);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Session session = (Session) o;

		return Objects.equals(id, session.id);
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
