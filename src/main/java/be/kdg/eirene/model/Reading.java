package be.kdg.eirene.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Reading {
	private final Timestamp timestamp;
	private BrainWave brainWave;
	private SensorData sensorData;

	public Reading(BrainWave brainWave, SensorData sensorData) {
		this.timestamp = new Timestamp(System.currentTimeMillis());
		this.brainWave = brainWave;
		this.sensorData = sensorData;
	}
	
	public boolean isValid() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this.brainWave != null && this.sensorData != null && this.timestamp != null;
	}
}
