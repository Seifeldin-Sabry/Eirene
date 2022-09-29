import jssc.SerialPort;
import jssc.SerialPortException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Brainwaves {
    public static void main(String[] args) throws SerialPortException {
        List<Integer> packet = new ArrayList<>();
        PacketParser parser = new PacketParser();
        SerialPort serialPort = new SerialPort("COM4");
        serialPort.openPort();//Open serial port
        serialPort.setParams(9600, 8, 0, 0);//Set params
        int counter = 0;
        while (true) {
                int[] buffer1 = serialPort.readIntArray(1);//Read 10 bytes from serial port
                // convert bytes to int
                // add bytes to a packet
            System.out.println();
            System.out.println(buffer1[0]);
                //packet.add(intBuffer1[0]);
                //check if packet is complete
                if(parser.isDataByte(packet)){
                    Map<String,Integer> data = parser.parse(packet);
                    System.out.println("SIGNAl: "+data.get("SIGNAL VALUE")+" ATTENTION VALUE: "+data.get("ATTENTION VALUE")+" MEDITATION VALUE: "+data.get("MEDITATION VALUE"));
                    packet.clear();
                    packet.add(170);
                    packet.add(170);
                }

                counter++;
                if(counter==1000) {
                    serialPort.closePort();
                    break;
                }

            }
        }
    }
