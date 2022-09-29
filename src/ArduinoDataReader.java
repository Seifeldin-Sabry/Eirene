import jssc.SerialPort;
import jssc.SerialPortException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class ArduinoDataReader {

    private final String portName = "/dev/tty.usbmodem11101";
    private HashMap<Sensor, List<DataReading>> sensorToData;
    private final String temperatureMatching = "-?\\d{1,2}\\.\\d{0,2}C";
    private final String humidityMatching = "\\d{1,2}\\.\\d{0,2}%";
    private final String heartMatching = "\\d{2,3}bpm";
    private final String soundMatching = "\\d{2,3}hz";
    StringBuilder currentReading = new StringBuilder();

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
                        String s = port.readString();
//                       String[] data = s.split(" ");
//                       System.out.println(s);
//                        processDataAndAddToMap(s);
                        //if the data ends in a %, our packet is full
                        //example: 105bpm 83db 21.70C 37.00%
                        if(s.contains("%")){
                            if("%".equals(s)) {
                                processDataAndAddToMap(currentReading.append("%").toString().split(" "));
                                return;
                            }
                            String[] data = s.split("%");
                            processDataAndAddToMap(currentReading.append(data[0]).append("%").toString().split(" "));
                            if(data.length == 1) return;
                            currentReading = new StringBuilder(data[1]);
                            System.out.println(sensorToData);
                            return;
                        }
                        currentReading.append(s);
                        System.out.println("Sound Readings");
                        System.out.println(sensorToData.get(Sensor.SOUND));
                        System.out.println("=".repeat(25));
                        System.out.println("=".repeat(25));
                        System.out.println("Heart Readings");
                        System.out.println(sensorToData.get(Sensor.HR));
                        System.out.println("=".repeat(25));
                        System.out.println("=".repeat(25));
                        System.out.println("Temp Readings");
                        System.out.println(sensorToData.get(Sensor.TEMPERATURE));
                        System.out.println("=".repeat(25));
                        System.out.println("=".repeat(25));
                        System.out.println("Humidity Readings");
                        System.out.println(sensorToData.get(Sensor.HUMIDITY));
                        System.out.println("=".repeat(25));
                        System.out.println("=".repeat(25));

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
            if(Pattern.matches(temperatureMatching, s)) addTemperatureReading(s);
            else if (Pattern.matches(humidityMatching, s)) addHumidityReading(s);
            else if (Pattern.matches(heartMatching, s)) addHeartReading(s);
            else if(Pattern.matches(soundMatching, s)) addSoundReading(s);
        }
    }

    private void addSoundReading(String s) {
        List<DataReading> list = sensorToData.get(Sensor.SOUND);
        String[] data = s.split("bpm");
        DataReading dataReading = new DataReading(Float.parseFloat(data[0]), Unit.HZ);
        if (list == null) {
            list = new ArrayList<>();
            list.add(dataReading);
            sensorToData.put(Sensor.SOUND, list);
        }else {
            list.add(dataReading);
        }
    }

    private void addHeartReading(String s) {
        List<DataReading> list = sensorToData.get(Sensor.HR);
        String[] data = s.split("bpm");
        DataReading dataReading = new DataReading(Float.parseFloat(data[0]), Unit.BPM);
        if (list == null) {
            list = new ArrayList<>();
            list.add(dataReading);
            sensorToData.put(Sensor.HR, list);
        }else {
            list.add(dataReading);
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

