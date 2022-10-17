package be.kdg.eirene.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SensorData {
	private int heartRate;
	private float temperature;
	private int sound;
	private float humidity;
	private int light;

	public SensorData(int heartRate, float temperature, int sound, float humidity, int light) {
		this.heartRate = heartRate;
		this.temperature = temperature;
		this.sound = sound;
		this.humidity = humidity;
		this.light = light;
	}

	@Override
	public String toString() {
		return "SensorData{" +
				"heartRate=" + heartRate +
				", temperature=" + temperature +
				", sound=" + sound +
				", humidity=" + humidity +
				", photo=" + light +
				'}';
	}
}
