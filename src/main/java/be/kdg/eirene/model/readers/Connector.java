package be.kdg.eirene.model.readers;

public class Connector {
    private final Readings readings;
    private final ArduinoDataReader arduinoDataReader;
    private final MindflexDataReader mindflexDataReader;


    public Connector(DataStream dataStream) {
        this.readings = new Readings();
        this.arduinoDataReader = new ArduinoDataReader(dataStream);
        this.mindflexDataReader = new MindflexDataReader(dataStream);
    }

    public void connectAll() throws PortNotFoundException {
        String mindFlexPortName = mindflexDataReader.findPort().orElseThrow(() -> new PortNotFoundException("Mindflex Port Not Found"));
        String arduinoPortName = arduinoDataReader.findPort().orElseThrow(() -> new PortNotFoundException("Arduino Port Not Found"));
        System.out.println("Arduino Port: " + arduinoPortName);
        System.out.println("Mindflex Port: " + mindFlexPortName);
        mindflexDataReader.connect(mindFlexPortName);
        arduinoDataReader.connect(arduinoPortName);
    }
}
