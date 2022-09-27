import jssc.SerialPort;
import jssc.SerialPortException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class ArduinoDataReader {

    private final String portName = "/dev/tty.usbmodem11401";
    private HashMap<Sensor, List<DataReading>> sensorToData;
    private final String temperatureMatching = "-?\\d{1,2}\\.\\d{0,2}C";
    private final String humidityMatching = "\\d{1,2}\\.\\d{0,2}%";

    public ArduinoDataReader() {
        this.sensorToData = new HashMap<>();
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
            port.addEventListener(event -> {

                if (event.isRXCHAR()) {
                    try{
                        String s = port.readString(6);
//                       String[] data = s.split(" ");
//                       System.out.println(s);
                        processDataAndAddToMap(s);
                        System.out.println(sensorToData);

                    } catch (SerialPortException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

        } catch (SerialPortException e) {
            e.printStackTrace();
        }

    }

    private void processDataAndAddToMap(String[] data) {
        for (String s: data) {
            if(Pattern.matches(temperatureMatching, s)) {
                addTemperatureReading(s);
            }
            else if (Pattern.matches(humidityMatching, s)) {
                addHumidityReading(s);
            }
        }
    }

    private void processDataAndAddToMap(String s) {
        if(Pattern.matches(temperatureMatching, s)) {
            addTemperatureReading(s);
        }
        else if (Pattern.matches(humidityMatching, s)) {
            addHumidityReading(s);
        }
    }
    private void addHumidityReading(String s) {
        List<DataReading> list = sensorToData.get(Sensor.HUMIDITY);
        String[] data = s.split("%");
        DataReading dataReading = new DataReading(Float.parseFloat(data[0]), Unit.PERCENT);
        if (list == null) {
            list = new ArrayList<>();
            list.add(dataReading);
            sensorToData.put(Sensor.HUMIDITY, list);
        }else {
            list.add(dataReading);
        }
    }

    private void addTemperatureReading(String s) {
        List<DataReading> list = sensorToData.get(Sensor.TEMPERATURE);
        String[] data = s.split("C");
        DataReading dataReading = new DataReading(Float.parseFloat(data[0]), Unit.C);
        if (list == null) {
            list = new ArrayList<>();
            list.add(dataReading);
            sensorToData.put(Sensor.TEMPERATURE, list);
        }else {
            list.add(dataReading);
        }
    }


}

