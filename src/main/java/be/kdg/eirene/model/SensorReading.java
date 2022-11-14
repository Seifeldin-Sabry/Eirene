package be.kdg.eirene.model;

import be.kdg.eirene.util.PostgreSQLEnumType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sensor_readings")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
@NoArgsConstructor
@Setter
@Getter
public final class SensorReading {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sensor_reading_id", nullable = false)
	private Long sensor_reading_id;

	@Column(name = "value", nullable = false)
	private float value;

	@Enumerated(EnumType.STRING)
	@Column(name = "unit", nullable = false)
	@Type(type = "pgsql_enum")
	private Unit unit;

	public SensorReading(float value, Unit unit) {
		this.value = value;
		this.unit = unit;
	}

	@Override
	public String toString() {
		return String.format("Value: %.2f\nUnit: %s\n", value, unit);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (SensorReading) obj;
		return Float.floatToIntBits(this.value) == Float.floatToIntBits(that.value) &&
				Objects.equals(this.unit, that.unit);
	}

	public float value() {
		return value;
	}

	public Unit unit() {
		return unit;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value, unit);
	}


}
