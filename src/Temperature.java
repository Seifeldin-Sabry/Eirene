import java.sql.Timestamp;
import java.util.Objects;

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


    public Timestamp getTempTime() {
        return tempTime;
    }

    public static Temperature parse(String s){
        //[SH], [47.00], [T], [24.34], [E]
        String[] data = s.split(" ");
        return new Temperature(1.0, 1.0);
    }

    @Override
    public String toString() {
        return String.format("Time: %s\t\tTemp: %.2f\t\tHumidity: %.2f%%", tempTime, temperature, humidity);
    }
}
