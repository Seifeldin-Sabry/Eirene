package main.java.be.kdg.eirene.model;

public class BrainWave {
    private int signal;
    private int level;

    public BrainWave(int signal, int level) {
        this.signal = signal;
        this.level = level;
    }

    public int getSignal() {
        return signal;
    }

    public void setSignal(int signal) {
        this.signal = signal;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "BrainWave{" +
                "signal=" + signal +
                ", level=" + level +
                '}';
    }
}
