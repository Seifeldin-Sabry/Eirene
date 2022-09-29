public enum Unit {
    C("C"), BPM("bpm"), PERCENT("%"), HZ("hz"), L("l");

    private String unit;

    Unit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }
}
