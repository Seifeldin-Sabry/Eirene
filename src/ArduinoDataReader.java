import jssc.SerialPort;
import jssc.SerialPortException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class ArduinoDataReader {

    private final String portName = "/dev/tty.usbmodem11101";
    private HashMap<Sensor, List<DataReading>> sensorToData;
    private final String temperatureMatching = String.format("-?\\d{1,2}\\.\\d{0,2}%s",Unit.C.getUnit());
    private final String humidityMatching = String.format("\\d{1,2}\\.\\d{0,2}%s",Unit.PERCENT.getUnit());
    private final String heartMatching = String.format("\\d{2,3}%s",Unit.BPM.getUnit());
    private final String soundMatching = String.format("\\d{2,3}%s",Unit.HZ.getUnit());
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

                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        if(s.contains("%")){
                            System.out.println(currentReading.toString());
                            if("%".equals(s)) {
                                String[] data = currentReading.append("%").toString().split(" ");
                                System.out.println(Arrays.toString(data) + "equals");
                                processDataAndAddToMap(data, timestamp);
                                currentReading = new StringBuilder();
                                printData();
                                return;
                            }
                            if(s.endsWith("%") && s.length() > 1) {
                                String[] data = currentReading.append(s).toString().split(" ");
                                System.out.println(Arrays.toString(data) + "ends");
                                processDataAndAddToMap(data, timestamp);
                                currentReading = new StringBuilder();
                                printData();
                                return;
                            }
                            int percentIdx = s.indexOf("%");
                            String beforePercent = s.substring(0, percentIdx);
                            String[] data = currentReading.append(beforePercent).append("%").toString().split(" ");
                            System.out.println(Arrays.toString(data) + " last if");
                            processDataAndAddToMap(data, timestamp);
                            String afterPercent = s.substring(percentIdx+1);
                            currentReading = new StringBuilder(afterPercent);
                            printData();
                            return;
                        }
                        currentReading.append(s);
                        printData();

                    } catch (SerialPortException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

        } catch (SerialPortException e) {
            e.printStackTrace();
        }

    }

    private void printData() {
        System.out.println("Sound Readings");
        System.out.println(sensorToData.get(Sensor.SOUND));
        System.out.println("=".repeat(25));
        System.out.println("Heart Readings");
        System.out.println(sensorToData.get(Sensor.HR));
        System.out.println("=".repeat(25));
        System.out.println("Temp Readings");
        System.out.println(sensorToData.get(Sensor.TEMPERATURE));
        System.out.println("=".repeat(25));
        System.out.println("Humidity Readings");
        System.out.println(sensorToData.get(Sensor.HUMIDITY));
        System.out.println("=".repeat(25));
    }

    private void processDataAndAddToMap(String[] data, Timestamp timestamp) {
        for (String s: data) {
            if(Pattern.matches(temperatureMatching, s)) addTemperatureReading(s, timestamp);
            else if (Pattern.matches(humidityMatching, s)) addHumidityReading(s, timestamp);
            else if (Pattern.matches(heartMatching, s)) addHeartReading(s, timestamp);
            else if(Pattern.matches(soundMatching, s)) addSoundReading(s, timestamp);
        }
    }

    private void addSoundReading(String s, Timestamp timestamp) {
        List<DataReading> list = sensorToData.get(Sensor.SOUND);
        String[] data = s.split(Sensor.SOUND.getUnit().getUnit());
        DataReading dataReading = new DataReading(timestamp, Float.parseFloat(data[0]), Unit.HZ);
        if (list == null) {
            list = new ArrayList<>();
            list.add(dataReading);
            sensorToData.put(Sensor.SOUND, list);
        }else {
            list.add(dataReading);
        }
    }

    private void addHeartReading(String s, Timestamp timestamp) {
        List<DataReading> list = sensorToData.get(Sensor.HR);
        String[] data = s.split(Sensor.HR.getUnit().getUnit());
        DataReading dataReading = new DataReading(timestamp, Float.parseFloat(data[0]), Unit.BPM);
        if (list == null) {
            list = new ArrayList<>();
            list.add(dataReading);
            sensorToData.put(Sensor.HR, list);
        }else {
            list.add(dataReading);
        }
    }

    private void addHumidityReading(String s, Timestamp timestamp) {
        List<DataReading> list = sensorToData.get(Sensor.HUMIDITY);
        String[] data = s.split(Sensor.HUMIDITY.getUnit().getUnit());
        DataReading dataReading = new DataReading(timestamp, Float.parseFloat(data[0]), Unit.PERCENT);
        if (list == null) {
            list = new ArrayList<>();
            list.add(dataReading);
            sensorToData.put(Sensor.HUMIDITY, list);
        }else {
            list.add(dataReading);
        }
    }

    private void addTemperatureReading(String s, Timestamp timestamp) {
        List<DataReading> list = sensorToData.get(Sensor.TEMPERATURE);
        String[] data = s.split(Sensor.TEMPERATURE.getUnit().getUnit());
        DataReading dataReading = new DataReading(timestamp, Float.parseFloat(data[0]), Unit.C);
        if (list == null) {
            list = new ArrayList<>();
            list.add(dataReading);
            sensorToData.put(Sensor.TEMPERATURE, list);
        }else {
            list.add(dataReading);
        }
    }


}

