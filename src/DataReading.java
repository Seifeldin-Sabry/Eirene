import java.sql.Timestamp;

public final class DataReading {
    private final Timestamp timestamp;
    private final float value;
    private final Unit unit;

    public DataReading(float value, Unit unit) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.value = value;
        this.unit = unit;
    }

    public DataReading(Timestamp timestamp, float value, Unit unit) {
        this.timestamp = timestamp;
        this.value = value;
        this.unit = unit;
    }

    public float getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("Time: %s\nValue: %.2f\nUnit: %s\n", timestamp, value, unit);
    }
}
