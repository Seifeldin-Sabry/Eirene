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

    @Override
    public String toString() {
        return String.format("Time: %s\t\tValue: %.2f\t\tUnit: %s",timestamp, value, unit);
    }
}
