package be.kdg.eirene.model;

import be.kdg.eirene.util.PostgreSQLEnumType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "sessions")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Session {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "session_id", nullable = false)
	private Long id;

	@Column(name = "start_time", nullable = false)
	private Timestamp startTime;

	@Column(name = "end_time", nullable = false)
	private Timestamp endTime;

	@Enumerated(EnumType.STRING)
	@Column(name = "session_type", nullable = false)
	@Type(type = "pgsql_enum")
	private SessionType type;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "session_id")
	@ToString.Exclude
	private List<Reading> readings;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	@ToString.Exclude
	private User user;

	public Session(SessionType type) {
		this.startTime = new Timestamp(System.currentTimeMillis());
		this.type = type;
		readings = new ArrayList<>();
	}

	/**
	 * Stops the session and sets the end time
	 */
	public void stop() {
		this.endTime = new Timestamp(System.currentTimeMillis());
	}

	/**
	 * Calculates the duration of the session in seconds.
	 * @return The duration of the session in seconds.
	 */
	public long getDuration() {
		return this.endTime.getTime() - this.startTime.getTime();
	}

	/**
	 * Calculates and returns the duration of the session from the start until time of invocation.
	 * @return the duration of the session from the start until time of invocation.
	 */
	public long getCurrentDuration() {
		return System.currentTimeMillis() - this.startTime.getTime();
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