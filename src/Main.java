import jssc.SerialPortException;

public class Main {
    public static void main(String[] args) throws SerialPortException {
//        final HashSet<String> ports = new HashSet<>(Arrays.asList(SerialPortList.getPortNames()));
//        System.out.println(ports);

        // ArduinoDataReader arduinoDataReader = new ArduinoDataReader();
        MindflexDataReader mindflexDataReader = new MindflexDataReader("COM6");
        // arduinoDataReader.connect();
        mindflexDataReader.connect();


    }
}
