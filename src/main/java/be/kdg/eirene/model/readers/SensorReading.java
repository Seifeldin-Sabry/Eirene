package be.kdg.eirene.model.readers;

import lombok.Getter;

import java.util.Objects;

@Getter
public final class SensorReading {
	private final float value;
	private final Unit unit;

	public SensorReading(float value, Unit unit) {
		this.value = value;
		this.unit = unit;
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
