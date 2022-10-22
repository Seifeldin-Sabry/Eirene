package be.kdg.eirene.model.readers;

import be.kdg.eirene.model.SessionType;
import jssc.SerialPortException;
import jssc.SerialPortList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws SerialPortException, InterruptedException, PortNotFoundException {
        final HashSet<String> ports = new HashSet<>(Arrays.asList(SerialPortList.getPortNames()));
        System.out.println(ports);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Session type [FOCUS / MEDITATION]: ");
        String input = scanner.nextLine();

        DataStream dataStream = null;
        switch (input.toUpperCase()) {
            case "FOCUS":
                dataStream = new DataStream(SessionType.FOCUS);
                break;
            case "MEDITATION":
                dataStream = new DataStream(SessionType.MEDITATION);
                break;
            default:
                System.out.println("Invalid input");
        }
        Connector connector = new Connector(dataStream);
        connector.connectAll();
    }
}
