package be.kdg.eirene.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Table (name = "SENSOR_DATA")
@NoArgsConstructor
@Immutable
@Getter
@Setter
@ToString
public class SensorData {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "sensor_data_id", nullable = false)
	private Long sensor_data_id;

	@Column (name = "heart_rate", nullable = false)
	private float heartRate;

	@Column
	private float temperature;

	@Column
	private float sound;

	@Column
	private float humidity;

	@Column
	private float light;

	public SensorData(float heartRate, float temperature, float sound, float humidity, float light) {
		this.heartRate = heartRate;
		this.temperature = temperature;
		this.sound = sound;
		this.humidity = humidity;
		this.light = light;
	}

	public float getDataOf(Sensor sensor) {
		return switch (sensor) {
			case HR -> heartRate;
			case TEMPERATURE -> temperature;
			case SOUND -> sound;
			case HUMIDITY -> humidity;
			case PHOTOTRANSISTOR -> light;
			case BRAINWAVE -> 0.0F;
		};
	}
}
