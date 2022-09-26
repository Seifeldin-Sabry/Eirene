import java.sql.Timestamp;

public class Temperature {

    private Timestamp tempTime;
    private double temperature;
    private double humidity;

    public Temperature(double temperature, double humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        tempTime = new Timestamp(System.currentTimeMillis());
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public Timestamp getTempTime() {
        return tempTime;
    }

    @Override
    public String toString() {
        return String.format("Time: %s\t\tTemp: %.2f\t\tHumidity: %.2f%%", tempTime, temperature, humidity);
    }
}
