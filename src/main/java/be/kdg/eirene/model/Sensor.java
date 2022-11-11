package be.kdg.eirene.model;

public enum Sensor {
    HR(Unit.BPM), TEMPERATURE(Unit.C), HUMIDITY(Unit.PERCENT), PHOTOTRANSISTOR(Unit.L), SOUND(Unit.DB), BRAINWAVE(Unit.PERCENT);

    private final Unit unit;

    Sensor(Unit unit) {
        this.unit = unit;
    }

    public Unit getUnit() {
        return unit;
    }
}
