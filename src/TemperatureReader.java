import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import java.util.Arrays;

public class TemperatureReader {

    private final String portName = "/dev/tty.usbmodem11101";
    private final Temperatures temperatures;

    public TemperatureReader() {
        temperatures = new Temperatures();
    }

    public TemperatureReader(Temperatures temperatures) {
        this.temperatures = temperatures;
    }

    public void connect() {
        SerialPort port = new SerialPort(portName);

        try {
            port.openPort();

            port.setParams(

                    SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE
            );
            port.addEventListener(new SerialPortEventListener() {
                @Override
                public void serialEvent(SerialPortEvent event) {

                    if (event.isRXCHAR()) {
                        try {
                            // String s = port.readString();
                            byte[] arr = port.readBytes(1);
                            System.out.println("arr length: " + arr.length +"\n arr contents:");
                            int[] intBuffer1 = new int[arr.length];
                            for (int i = 0; i < arr.length; i++) {
                                intBuffer1[i] = arr[i] & 0xFF;
                            }
                            System.out.println(Arrays.toString(intBuffer1));
                         //   packet.append(s);

                            if (validatePacket(packet.toString())) {
                                System.out.println("DATA DETECTED \n");
                                System.out.println(packet);
//                                Temperature temp = Temperature.parse(s);
//                                temperatures.addTemperature(temp);
//                                System.out.println(temp);
                                packet.delete(0, packet.length()-1);
                            }
                        } catch (SerialPortException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    }

                }
            });

        } catch (SerialPortException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
