import jssc.SerialPort;
import jssc.SerialPortException;

import java.util.ArrayList;
import java.util.List;

public class Brainwaves {
    public static void main(String[] args) throws SerialPortException {
        List<Integer> packet = new ArrayList<>();
        PacketParser parser = new PacketParser();
        SerialPort serialPort = new SerialPort("COM4");
        serialPort.openPort();//Open serial port
        serialPort.setParams(9600, 8, 0, 0);//Set params
        int counter = 0;
        while (true) {
                byte[] buffer1 = serialPort.readBytes(1);//Read 10 bytes from serial port
                // convert bytes to int
                int[] intBuffer1 = new int[buffer1.length];
                for (int i = 0; i < buffer1.length; i++) {
                    intBuffer1[i] = buffer1[i] & 0xFF;
                }

                // add bytes to a packet
                packet.add(intBuffer1[0]);
                //check if packet is complete
                if(parser.isDataByte(packet)){
                    System.out.println(parser.parse(packet,false));
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
