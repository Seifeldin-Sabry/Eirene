package be.kdg.eirene.model;

import lombok.Getter;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.Objects;

@Immutable
@Entity
@Table(name = "SENSORDATA")
@Getter
public final class SensorReading {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sensor_data_id", nullable = false)
	private Long id;
	private float value;
	private Unit unit;

	public SensorReading(float value, Unit unit) {
		this.value = value;
		this.unit = unit;
	}

	public SensorReading() {

	}

	@Override
	public String toString() {
		return String.format("Value: %.2f\nUnit: %s\n", value, unit);
	}

	public float value() {return value;}

	public Unit unit() {return unit;}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (SensorReading) obj;
		return Float.floatToIntBits(this.value) == Float.floatToIntBits(that.value) &&
				Objects.equals(this.unit, that.unit);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value, unit);
	}

}
