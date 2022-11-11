package be.kdg.eirene.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Type;

import javax.persistence.*;

//@Entity
//@Table(name = "SENSORDATA")
//@Immutable
@Getter
@Setter
public class SensorData {
//	@Setter(AccessLevel.NONE)
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "sensor_data_id", nullable = false)
	private Long id;

//	@Column(name = "heart_rate", nullable = false)
//	@Type(type = "org.hibernate.type.IntegerType")
	private SensorReading heartRate;

//	@Column(name = "temperature", nullable = false)
//	@Type(type = "org.hibernate.type.FloatType")
	private SensorReading temperature;

//	@Column(name = "sound", nullable = false)
//	@Type(type = "org.hibernate.type.IntegerType")
	private SensorReading sound;

//	@Column(name = "humidity", nullable = false)
//	@Type(type = "org.hibernate.type.FloatType")
	private SensorReading humidity;

//	@Column(name = "light", nullable = false)
//	@Type(type = "org.hibernate.type.IntegerType")
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
