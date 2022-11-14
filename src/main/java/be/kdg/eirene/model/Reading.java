package be.kdg.eirene.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "readings")
@Immutable
@NoArgsConstructor
@Getter
@Setter
public class Reading {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reading_id", nullable = false)
	private Long reading_id;

	@Column(name = "time_stamp", nullable = false)
	private Timestamp timestamp;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "brainwave_id", nullable = false)
	private BrainWaveReading brainWave;

	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "sensor_data_id", nullable = false)
	private SensorData sensorData;

	@ManyToOne
	@JoinColumn (name = "session_id")
	private Session session;

	public Reading(Timestamp timestamp, BrainWaveReading brainWave, SensorData sensorData) {
		this.timestamp = timestamp;
		this.brainWave = brainWave;
		this.sensorData = sensorData;
	}

	@Override
	public String toString() {
		return String.format("Time: %s\nBrainwave: %s\nSensorData: %s\n", timestamp, brainWave, sensorData);
	}
}
