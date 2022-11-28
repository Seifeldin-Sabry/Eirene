package be.kdg.eirene.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Table (name = "SENSOR_DATA")
@NoArgsConstructor
@Immutable
@Getter
@Setter
public class SensorData {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "sensor_data_id", nullable = false)
	private Long sensor_data_id;

	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "heart_rate_id", nullable = false)
	private SensorReading heartRate;

	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "temperature_id", nullable = false)
	private SensorReading temperature;

	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "sound_id", nullable = false)
	private SensorReading sound;

	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "humidity_id", nullable = false)
	private SensorReading humidity;

	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "light_id", nullable = false)
	private SensorReading light;

	@OneToOne (mappedBy = "sensorData")
	private Reading reading;

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

	public void setReading(Reading reading) {
		this.reading = reading;
	}
}
