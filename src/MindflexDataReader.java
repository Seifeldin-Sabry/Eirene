import jssc.SerialPort;
import jssc.SerialPortException;
import main.java.be.kdg.eirene.model.BrainWave;
import main.java.be.kdg.eirene.model.Reading;
import main.java.be.kdg.eirene.model.SessionType;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MindflexDataReader {

    Reading dataStream;

    public MindflexDataReader(Reading dataStream) {
        this.dataStream = dataStream;
    }

    /**
     * Connects to the Mindflex headset and updates the data stream with the data read from the headset.
     *
     * @param portName the name of the port to connect to
     * @throws SerialPortException
     */
    public void connect(String portName) throws SerialPortException {
        SerialPort serialPort = new SerialPort(portName);
        PacketParser parser = new PacketParser();
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
                Map<String, Integer> data = parser.parse(packet);
                packet = new ArrayList<>();
                try {
                    if (dataStream.getType() == SessionType.FOCUS) {
                        dataStream.setBrainWave(new BrainWave(data.get("SIGNAL VALUE"), data.get("ATTENTION VALUE")));
                    } else {
                        dataStream.setBrainWave(new BrainWave(data.get("SIGNAL VALUE"), data.get("MEDITATION VALUE")));
                    }
                    dataStream.setReadTime(new Timestamp(System.currentTimeMillis()));
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } catch (SerialPortException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
