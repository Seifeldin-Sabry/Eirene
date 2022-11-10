package be.kdg.eirene.model.readers;

public class Connector {
	private final ArduinoDataReader arduinoDataReader;
	private final MindflexDataReader mindflexDataReader;


	public Connector() {
		Reading reading = new Reading();
		this.arduinoDataReader = new ArduinoDataReader(reading);
		this.mindflexDataReader = new MindflexDataReader(reading);
	}

	public void connectAll() throws PortNotFoundException {
		String mindFlexPortName = mindflexDataReader.findPort()
		                                            .orElseThrow(() -> new PortNotFoundException("Mindflex Port Not Found"));
		String arduinoPortName = arduinoDataReader.findPort()
		                                          .orElseThrow(() -> new PortNotFoundException("Arduino Port Not Found"));
		System.out.println("Arduino Port: " + arduinoPortName);
		System.out.println("Mindflex Port: " + mindFlexPortName);
		mindflexDataReader.connect(mindFlexPortName);
		arduinoDataReader.connect(arduinoPortName);
	}
}
