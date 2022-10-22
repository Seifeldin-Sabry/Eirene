package be.kdg.eirene.model.readers;

import be.kdg.eirene.model.SessionType;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class DataStream {
    private final SessionType type;
    private Timestamp readTime;
    private BrainWave brainWave;
    private SensorData sensorData;

    public DataStream(SessionType type) {
        this.type = type;
    }


    public void clear() {
        this.brainWave = null;
        this.sensorData = null;
        this.readTime = null;
    }

    public boolean isValid() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.brainWave != null && this.sensorData != null && this.readTime != null;
    }

    public void logData() {
        if (isValid()) {
            System.out.println("ReadTime: " + this.readTime + " BrainWave: " + this.brainWave + " SensorData: " + this.sensorData);
        }
    }

    @Override
    public String toString() {
        return String.format("Time: %s\nBrainwave: %s\nSensorData: %s\n", readTime, brainWave, sensorData);
    }
}
