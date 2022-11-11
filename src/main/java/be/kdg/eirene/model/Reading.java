package be.kdg.eirene.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "readings")
@Getter
@Setter
public class Reading {
	@Setter(AccessLevel.NONE)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "reading_id", nullable = false)
	private Long id;

	@Column(name = "time_stamp", nullable = false)
	private Timestamp timestamp;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "brainwave_id", nullable = false)
	private BrainWaveReading brainWave;

	@Transient
//	@OneToOne(cascade = {CascadeType.ALL})
//	@JoinColumn(name = "sensor_data_id", nullable = false)
	private SensorData sensorData;

	public void clear() {
		this.brainWave = null;
		this.sensorData = null;
		this.timestamp = null;
	}

	public boolean isValid() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this.brainWave != null && this.sensorData != null && this.timestamp != null;
	}

	public void logData() {
		if (isValid()) {
			System.out.println("Timestamp: " + this.timestamp + " BrainWave: " + this.brainWave + " SensorData: " + this.sensorData);
		}
	}

	@Override
	public String toString() {
		return String.format("Time: %s\nBrainwave: %s\nSensorData: %s\n", timestamp, brainWave, sensorData);
	}
}
