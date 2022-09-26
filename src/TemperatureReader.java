import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortException;

public class TemperatureReader {

    private final String portName = "/dev/tty.usbmodem11401";
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
            port.addEventListener((SerialPortEvent event)->{

                if(event.isRXCHAR()) {

                    try {
                        String s = port.readString();

                        //1. Split on newlines

                        //2. 

                        System.out.print(s);
                    } catch (SerialPortException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }

            });

        } catch (SerialPortException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
