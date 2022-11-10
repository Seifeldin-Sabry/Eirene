package be.kdg.eirene.model.readers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SensorData {
	private SensorReading heartRate;
	private SensorReading temperature;
	private SensorReading sound;
	private SensorReading humidity;
	private SensorReading light;

	public SensorData() {
	}

	public SensorData(float heartRate, float temperature, float sound, float humidity, float light) {
		this.heartRate = new SensorReading(heartRate, Unit.BPM);
		this.temperature = new SensorReading(temperature, Unit.C);
		this.sound = new SensorReading(sound, Unit.DB);
		this.humidity = new SensorReading(humidity, Unit.PERCENT);
		this.light = new SensorReading(light, Unit.L);
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
