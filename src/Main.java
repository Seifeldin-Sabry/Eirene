//import jssc.SerialPort;
//import jssc.SerialPortException;
//import jssc.SerialPortException;
//import jssc.SerialPortList;

import com.fazecast.jSerialComm.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import main.java.be.kdg.eirene.model.Reading;
import main.java.be.kdg.eirene.model.SessionType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws SerialPortException {
//        final HashSet<String> ports = new HashSet<>(Arrays.asList(SerialPortList.getPortNames()));
//        System.out.println(ports);
//        ArduinoDataReader dataReader = new ArduinoDataReader();
//        dataReader.connect(findPort(false));
//        MindflexDataReader mindflexDataReader = new MindflexDataReader(findPort(true));
//        mindflexDataReader.connect();
//        SerialPort[] serialPort = SerialPort.getCommPorts();
//        Arrays.stream(serialPort).forEach(p -> {
//            System.out.println(p.getSystemPortName() + " " + p.getDescriptivePortName());
//        });


        //    System.out.println(findPort(false));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Session type [FOCUS / MEDITATION]: ");
        String input = scanner.nextLine();

        Reading dataStream = null;
        switch (input.toUpperCase()) {
            case "FOCUS":
                dataStream = new Reading(SessionType.FOCUS);
                break;
            case "MEDITATION":
                dataStream = new Reading(SessionType.MEDITATION);
                break;
            default:
                System.out.println("Invalid input");
        }
        readData(dataStream);
    }

    /**
     * Reads data from the Mindflex headset and the arduino sensors, synchronizes them and then returns them.
     *
     * @param dataStream the data stream to update with the data read from the headset and the arduino sensors
     */
    private static void readData(Reading dataStream) throws SerialPortException {

        MindflexDataReader mindflexDataReader = new MindflexDataReader(dataStream);
        ArduinoDataReader arduinoDataReader = new ArduinoDataReader(dataStream);


        arduinoDataReader.connect("COM10");
        mindflexDataReader.connect("COM4");
        while (true) {
            if (dataStream.isValid()) {
                System.out.println("Reading data...");
                System.out.println(dataStream.getReadTime() + " " + dataStream.getBrainWave().getSignal() + " " + dataStream.getBrainWave() + " " + dataStream.getSensorData());
                dataStream.clear();
            }

        }
    }

    public static String findPort(boolean mindFlex) {
        final HashSet<String> ports = new HashSet<>(Arrays.asList(SerialPortList.getPortNames()));
        SerialPort[] serialPort = SerialPort.getCommPorts();
        System.out.println(Arrays.toString(serialPort));
        boolean isMacOS = ports.stream().anyMatch(port -> port.contains("/dev/"));
        if (mindFlex && isMacOS) {
            return ports.stream().filter(port -> port.contains("tty.HC-06")).toList().get(0);
        }
        if (isMacOS) {
            return ports.stream().filter(port -> port.contains("/dev/tty.usbmodem")).toList().get(0);
        }
        Pattern p = Pattern.compile("(COM\\d+)");   // the pattern to search for
        Matcher m;
        //windows and arduino
        if (!mindFlex) {
            String arduinoPortName = Arrays.stream(serialPort).map(SerialPort::getDescriptivePortName).filter(port -> port.contains("Arduino")).toList().get(0);
            System.out.println(arduinoPortName);
            m = p.matcher(arduinoPortName);
            while (m.find()) {
                String group = m.group();
                int start = m.start();
                int end = m.end();
                System.out.println(group + " " + start + " " + end);
                return group;
            }
            return "";
        }
        // mindflex and not mac
        String arduinoPortName = Arrays.stream(serialPort).map(port -> String.format("%s %s", port.getSystemPortName(), port)).filter(port -> port.contains("Dev B")).toList().get(0);
        System.out.println(arduinoPortName);
        m = p.matcher(arduinoPortName);
        return m.group(2);
    }
}
