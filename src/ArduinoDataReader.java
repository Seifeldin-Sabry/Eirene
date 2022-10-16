import jssc.SerialPort;
import jssc.SerialPortException;
import main.java.be.kdg.eirene.model.Reading;
import main.java.be.kdg.eirene.model.SensorData;

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
    private final HashMap<Sensor, List<DataReading>> sensorToData;
    StringBuilder currentReading = new StringBuilder();

    Reading dataStream;

    public ArduinoDataReader(Reading dataStream) {
        this.dataStream = dataStream;
        this.sensorToData = new HashMap<>();
    }


    /**
     * Connects to the Arduino
     *
     * @param portName the name of the port to connect to
     * @throws SerialPortException if the port is not found
     */
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
                            if ("%".equals(s)) {
                                String[] data = currentReading.append("%").toString().split(" ");
                                processDataAndAddToMap(data, timestamp);
                                currentReading = new StringBuilder();
                                return;
                            }
                            if (s.endsWith("%") && s.length() > 1) {
                                String[] data = currentReading.append(s).toString().split(" ");
                                processDataAndAddToMap(data, timestamp);
                                currentReading = new StringBuilder();
                                return;
                            }
                            int percentIdx = s.indexOf("%");
                            String beforePercent = s.substring(0, percentIdx);
                            String[] data = currentReading.append(beforePercent).append("%").toString().split(" ");
                            processDataAndAddToMap(data, timestamp);
                            String afterPercent = s.substring(percentIdx + 1);
                            currentReading = new StringBuilder(afterPercent);
                            return;
                        }
                        currentReading.append(s);
                        combineData();

                    } catch (SerialPortException | NullPointerException e) {
                        System.out.println("ERROR IN ARDUINO DATA READER: " + e);
                    }
                }
            });

        } catch (SerialPortException e) {
            e.printStackTrace();
        }

    }

    /**
     * Combines data that is split up into multiple packets into one packet, updates the data stream
     */
    private void combineData() {
        try {
            List<DataReading> heartData = sensorToData.get(Sensor.HR);
            List<DataReading> soundData = sensorToData.get(Sensor.SOUND);
            List<DataReading> tempData = sensorToData.get(Sensor.TEMPERATURE);
            List<DataReading> humid = sensorToData.get(Sensor.HUMIDITY);
            List<DataReading> photo = sensorToData.get(Sensor.PHOTOTRANSISTOR);

            SensorData sensorData = new SensorData();
            sensorData.setHeartRate((int) heartData.get(heartData.size() - 1).getValue());
            sensorData.setTemperature(tempData.get(tempData.size() - 1).getValue());
            sensorData.setHumidity(humid.get(humid.size() - 1).getValue());
            sensorData.setSound((int) soundData.get(soundData.size() - 1).getValue());
            sensorData.setPhoto((int) photo.get(photo.size() - 1).getValue());
            dataStream.setSensorData(sensorData);

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

