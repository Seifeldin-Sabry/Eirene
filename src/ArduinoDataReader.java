import jssc.SerialPort;
import jssc.SerialPortException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class ArduinoDataReader {

    private final String temperatureMatching = String.format("-?\\d{1,2}\\.\\d{0,2}%s", Unit.C.getUnit());
    private final String humidityMatching = String.format("\\d{1,2}\\.\\d{0,2}%s", Unit.PERCENT.getUnit());
    private final String heartMatching = String.format("\\d{2,3}%s", Unit.BPM.getUnit());
    private final String soundMatching = String.format("\\d{2,3}%s", Unit.DB.getUnit());
    private final String lightMatching = String.format("\\d{1,3}%s", Unit.L.getUnit());
    StringBuilder currentReading = new StringBuilder();
    private final HashMap<Sensor, List<DataReading>> sensorToData;

    public ArduinoDataReader() {
        this.sensorToData = new HashMap<>();
    }


    public void connect(String portName) {
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
                    try {
                        String s = port.readString();
//                       String[] data = s.split(" ");
//                       System.out.println(s);
//                        processDataAndAddToMap(s);
                        //if the data ends in a %, our packet is full
                        //example: 23l 105bpm 83db 21.70C 37.00%
                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        if (s.contains("%")) {
                            System.out.println(currentReading.toString());
                            if ("%".equals(s)) {
                                String[] data = currentReading.append("%").toString().split(" ");
                                processDataAndAddToMap(data, timestamp);
                                currentReading = new StringBuilder();
                                printData();
                                return;
                            }
                            if (s.endsWith("%") && s.length() > 1) {
                                String[] data = currentReading.append(s).toString().split(" ");
                                processDataAndAddToMap(data, timestamp);
                                currentReading = new StringBuilder();
                                printData();
                                return;
                            }
                            int percentIdx = s.indexOf("%");
                            String beforePercent = s.substring(0, percentIdx);
                            String[] data = currentReading.append(beforePercent).append("%").toString().split(" ");
                            processDataAndAddToMap(data, timestamp);
                            String afterPercent = s.substring(percentIdx + 1);
                            currentReading = new StringBuilder(afterPercent);
                            printData();
                            return;
                        }
                        currentReading.append(s);

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
        try {
            System.out.println("Sound Readings");
            List<DataReading> soundData = sensorToData.get(Sensor.SOUND);
            System.out.println(soundData.get(soundData.size() - 1));
            System.out.println("Heart Readings");
            List<DataReading> heartData = sensorToData.get(Sensor.HR);
            System.out.println(heartData.get(heartData.size() - 1));
            System.out.println("Temp Readings");
            List<DataReading> tempData = sensorToData.get(Sensor.TEMPERATURE);
            System.out.println(tempData.get(tempData.size() - 1));
            System.out.println("Humidity Readings");
            List<DataReading> humid = sensorToData.get(Sensor.HUMIDITY);
            System.out.println(humid.get(humid.size() - 1));
            List<DataReading> photo = sensorToData.get(Sensor.PHOTOTRANSISTOR);
            System.out.println(photo.get(photo.size() - 1));
        } catch (NullPointerException ignored) {

        }

    }

    private void processDataAndAddToMap(String[] data, Timestamp timestamp) {
        for (String s : data) {
            if (Pattern.matches(temperatureMatching, s)) addTemperatureReading(s, timestamp);
            else if (Pattern.matches(humidityMatching, s)) addHumidityReading(s, timestamp);
            else if (Pattern.matches(heartMatching, s)) addHeartReading(s, timestamp);
            else if (Pattern.matches(soundMatching, s)) addSoundReading(s, timestamp);
            else if (Pattern.matches(lightMatching, s)) addLightReading(s, timestamp);
        }
    }

    private void addLightReading(String s, Timestamp timestamp) {
        List<DataReading> list = sensorToData.get(Sensor.PHOTOTRANSISTOR);
        Unit unit = Sensor.PHOTOTRANSISTOR.getUnit();
        String[] data = s.split(unit.getUnit());
        DataReading dataReading = new DataReading(timestamp, Float.parseFloat(data[0]), unit);
        if (list == null) {
            list = new ArrayList<>();
            list.add(dataReading);
            sensorToData.put(Sensor.PHOTOTRANSISTOR, list);
        } else {
            list.add(dataReading);
        }
    }

    private void addSoundReading(String s, Timestamp timestamp) {
        List<DataReading> list = sensorToData.get(Sensor.SOUND);
        String[] data = s.split(Sensor.SOUND.getUnit().getUnit());
        DataReading dataReading = new DataReading(timestamp, Float.parseFloat(data[0]), Unit.DB);
        if (list == null) {
            list = new ArrayList<>();
            list.add(dataReading);
            sensorToData.put(Sensor.SOUND, list);
        } else {
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
        } else {
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
        } else {
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
        } else {
            list.add(dataReading);
        }
    }


}

