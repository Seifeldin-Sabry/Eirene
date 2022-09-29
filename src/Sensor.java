public enum Sensor {
    HR(Unit.BPM), TEMPERATURE(Unit.C), HUMIDITY(Unit.PERCENT), PHOTOTRANSISTOR(Unit.L), SOUND(Unit.HZ), BRAINWAVE(Unit.PERCENT);

    private Unit unit;

    Sensor(Unit unit) {
        this.unit = unit;
    }

    public Unit getUnit() {
        return unit;
    }
}
