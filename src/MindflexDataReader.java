import jssc.SerialPort;
import jssc.SerialPortException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MindflexDataReader {

    //How are we planning on doing this??


    private final SerialPort serialPort;

    public MindflexDataReader(String portName) {
        this.serialPort = new SerialPort(portName);
    }

    public void connect() throws SerialPortException {
        PacketParser parser = new PacketParser();
        SerialPort serialPort = new SerialPort("COM4");
        serialPort.openPort();//Open serial port
        serialPort.setParams(9600, 8, 0, 0);//Set params
        serialPort.addEventListener(event -> {
            try {
                List<Integer> packet = new ArrayList<>();

                //check if packet is complete
                while (!parser.isDataByte(packet)) {
                    int[] buffer1 = serialPort.readIntArray(1); // read byte from the headset
                    packet.add(buffer1[0]);
                }
                System.out.println(packet);
                Map<String, Integer> data = parser.parse(packet);
                System.out.println("SIGNAl: " + data.get("SIGNAL VALUE") + " FOCUS VALUE: " + data.get("ATTENTION VALUE") + " MEDITATION VALUE: " + data.get("MEDITATION VALUE"));
                packet = new ArrayList<>();
                System.out.println(packet);
                packet.add(170);
                packet.add(170);
            } catch (SerialPortException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
