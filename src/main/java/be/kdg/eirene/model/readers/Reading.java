package be.kdg.eirene.model.readers;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Reading {
	private Timestamp timestamp;
	private BrainWaveReading brainWave;
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
