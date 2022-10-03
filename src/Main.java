import jssc.SerialPortException;
import jssc.SerialPortList;

import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws SerialPortException {
//        final HashSet<String> ports = new HashSet<>(Arrays.asList(SerialPortList.getPortNames()));
//        System.out.println(ports);
        MindflexDataReader mindflexDataReader = new MindflexDataReader(findPort(true));
        mindflexDataReader.connect();
    }

    public static String findPort(boolean mindFlex) {
        final HashSet<String> ports = new HashSet<>(Arrays.asList(SerialPortList.getPortNames()));
        boolean isMacOS = ports.stream().anyMatch(port -> port.contains("/dev/"));
        if (mindFlex && isMacOS) {
            return ports.stream().filter(port -> port.contains("tty.HC-06")).toList().get(0);
        }
        //need a windows to know how the ports look
//        if(mindFlex){
//            ports.stream().filter(port -> port.contains("COM6"))
//        }
        if (!mindFlex && isMacOS) {
            return ports.stream().filter(port -> port.contains("/dev/tty.usbmodem")).toList().get(0);
        }
        // not mindflex and not mac
        return "";
    }
}
