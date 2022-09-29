public enum Unit {
    C("C"), BPM("bpm"), PERCENT("%"), HZ("db");

    private String unit;

    Unit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }
}
