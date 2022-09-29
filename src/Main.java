import jssc.SerialPortList;

import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
//        final HashSet<String> ports = new HashSet<>(Arrays.asList(SerialPortList.getPortNames()));
//        System.out.println(ports);


        ArduinoDataReader arduinoDataReader = new ArduinoDataReader();
        arduinoDataReader.connect();

    }
}
