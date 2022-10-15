public enum Unit {
    C("C"), BPM("bpm"), PERCENT("%"), DB("db"), L("l");

    private String unit;

    Unit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }
}
