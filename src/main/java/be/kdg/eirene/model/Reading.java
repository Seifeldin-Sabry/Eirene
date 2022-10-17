package be.kdg.eirene.model;

import java.sql.Timestamp;

public class Reading {
    private final SessionType type;
    private Timestamp readTime;
    private BrainWave brainWave;
    private SensorData sensorData;

    public Reading(SessionType type) {
        this.type = type;
    }

    public Timestamp getReadTime() {
        return readTime;
    }

    public void setReadTime(Timestamp readTime) {
        this.readTime = readTime;
    }

    public BrainWave getBrainWave() {
        return brainWave;
    }

    public void setBrainWave(BrainWave brainWave) {
        this.brainWave = brainWave;
    }

    public SensorData getSensorData() {
        return sensorData;
    }

    public void setSensorData(SensorData sensorData) {
        this.sensorData = sensorData;
    }

    public SessionType getType() {
        return type;
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
}
