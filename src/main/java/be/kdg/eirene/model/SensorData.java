package be.kdg.eirene.model;

public class SensorData {
    private int heartRate;
    private float temperature;
    // private boolean/int light;
    private int sound;
    private float humidity;
    private int photo;

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public int getSound() {
        return sound;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "SensorData{" +
                "heartRate=" + heartRate +
                ", temperature=" + temperature +
                ", sound=" + sound +
                ", humidity=" + humidity +
                ", photo=" + photo +
                '}';
    }
}
