package be.kdg.eirene.model.readers;

import jssc.SerialPort;
import jssc.SerialPortException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

public class ArduinoDataReader extends Reader {

    private final String temperatureMatching = String.format("-?\\d{1,2}\\.\\d{0,2}%s", Unit.C.getUnit());
    private final String humidityMatching = String.format("\\d{1,2}\\.\\d{0,2}%s", Unit.PERCENT.getUnit());
    private final String heartMatching = String.format("\\d{2,3}%s", Unit.BPM.getUnit());
    private final String soundMatching = String.format("\\d{2,3}%s", Unit.DB.getUnit());
    private final String lightMatching = String.format("\\d{1,3}%s", Unit.L.getUnit());
    private final HashMap<Sensor, List<DataReading>> sensorToData;
    String packetPattern = String.format("(.*)?\\d+%s \\d+%s \\d+%s \\d+\\.?\\d+%s \\d+\\.?\\d+%s(.*)?",
            Unit.L.getUnit(),
            Unit.BPM.getUnit(),
            Unit.DB.getUnit(),
            Unit.C.getUnit(),
            Unit.PERCENT.getUnit());

    public ArduinoDataReader(DataStream dataStream) {
        super(dataStream);
        this.sensorToData = new HashMap<>();
    }

    @Override
    public Optional<String> findPort() {
        HashSet<String> ports = isMacOS() ? new HashSet<>(getAllPorts()
                .parallelStream()
                .filter(s -> s.contains("tty")).toList()) : getAllPorts();
        LocalDateTime startTime = LocalDateTime.now();
        if (ports.isEmpty()) return Optional.empty();
        ArrayList<SerialPort> serialPorts = new ArrayList<>();
        for (String port : ports) {
            startEventListener(serialPorts, port);
        }
        boolean timePassed = false;
        while (getPortName() == null && !timePassed) {
            LocalDateTime here = LocalDateTime.now();
            long secondsPassed = ChronoUnit.SECONDS.between(startTime, here);
            timePassed = secondsPassed == getMAX_DURATION();
        }
        serialPorts.forEach(port -> {
            try {
                port.removeEventListener();
            } catch (SerialPortException ignored) {
            }
        });
        return Optional.ofNullable(getPortName());
    }

    private void startEventListener(ArrayList<SerialPort> serialPorts, String port) {
        AtomicBoolean arduinoPortFound = new AtomicBoolean(false);
        AtomicInteger numberOfTries = new AtomicInteger(0);
        SerialPort serialPort = new SerialPort(port);
        serialPorts.add(serialPort);
        AtomicReference<StringBuilder> currentReading = new AtomicReference<>(new StringBuilder());
        try {
            serialPort.openPort();
            serialPort.setParams(
                    SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE
            );
            serialPort.addEventListener(event -> {
                System.out.println("in event for port: " + port);
                if (numberOfTries.get() == getMAX_TRIES()) {
                    System.out.println("REACHED MAX " + port);
                    try {
                        serialPort.removeEventListener();
                        serialPort.closePort();
                    } catch (SerialPortException ignored) {
                    }
                } else numberOfTries.incrementAndGet();
                if (!event.isRXCHAR()) return;
                String dataIncoming;
                try {
                    dataIncoming = serialPort.readString();
                } catch (SerialPortException | NullPointerException ignored) {
                    dataIncoming = "";
                }
                if (dataIncoming == null) return;
                if (dataIncoming.contains("%")) {
                    String data = null;
                    if ("%".equals(dataIncoming)) {
                        data = currentReading.get().append("%").toString();
                    } else if (dataIncoming.endsWith("%") && dataIncoming.length() > 1) {
                        data = currentReading.get().append(dataIncoming).toString();
                    } else if (!dataIncoming.endsWith("%") && dataIncoming.length() > 1) {
                        int percentIdx = dataIncoming.indexOf("%");
                        String beforePercent = dataIncoming.substring(0, percentIdx);
                        data = currentReading.get().append(beforePercent).append("%").toString();
                        String afterPercent = dataIncoming.substring(percentIdx + 1);
                        currentReading.set(new StringBuilder(afterPercent));
                    }
                    arduinoPortFound.set(isArduinoPacket(data));
                    System.out.println(arduinoPortFound.get());
                    if (arduinoPortFound.get()) {
                        setSerialPort(port);
                        removeEventListenerAndClosePort(serialPort);
                    }

                }
                currentReading.get().append(dataIncoming);
                combineData();
            });

        } catch (SerialPortException ignored) {
            removeEventListenerAndClosePort(serialPort);
            serialPorts.remove(serialPort);
        }
    }

    private boolean isArduinoPacket(String data) {
        return Pattern.matches(packetPattern, data);
    }

    @Override
    void connect(String port) throws PortNotFoundException {
        AtomicReference<StringBuilder> currentReading = new AtomicReference<>(new StringBuilder());
        try {
            SerialPort serialPort = new SerialPort(port);
            serialPort.openPort();
            serialPort.setParams(
                    SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE
            );
            serialPort.addEventListener(serialPortEvent -> {
                if (serialPortEvent.isRXCHAR()) {
                    try {
                        String s;
                        try {
                            s = serialPort.readString();
                        } catch (SerialPortException e) {
                            s = "";
                        }

                        if (s.contains("%")) {
                            String[] data;
                            if ("%".equals(s)) {
                                data = currentReading.get().append("%").toString().split(" ");
                                processDataAndAddToMap(data);
                                currentReading.set(new StringBuilder());
                                return;
                            }
                            if (s.endsWith("%") && s.length() > 1) {
                                data = currentReading.get().append(s).toString().split(" ");
                                processDataAndAddToMap(data);
                                currentReading.set(new StringBuilder());
                                return;
                            }
                            int percentIdx = s.indexOf("%");
                            String beforePercent = s.substring(0, percentIdx);
                            data = currentReading.get().append(beforePercent).append("%").toString().split(" ");
                            processDataAndAddToMap(data);
                            String afterPercent = s.substring(percentIdx + 1);
                            currentReading.set(new StringBuilder(afterPercent));
                            return;
                        }
                        currentReading.get().append(s);
                        combineData();

                    } catch (NullPointerException e) {
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
            sensorData.setLight((int) photo.get(photo.size() - 1).getValue());
            getDataStream().setSensorData(sensorData);
            getDataStream().logData();

        } catch (NullPointerException ignored) {

        }

    }

    private void processDataAndAddToMap(String[] data) {
        for (String s : data) {
            if (Pattern.matches(temperatureMatching, s)) addTemperatureReading(s);
            else if (Pattern.matches(humidityMatching, s)) addHumidityReading(s);
            else if (Pattern.matches(heartMatching, s)) addHeartReading(s);
            else if (Pattern.matches(soundMatching, s)) addSoundReading(s);
            else if (Pattern.matches(lightMatching, s)) addLightReading(s);
        }
    }

    private void addLightReading(String s) {
        List<DataReading> list = sensorToData.get(Sensor.PHOTOTRANSISTOR);
        Unit unit = Sensor.PHOTOTRANSISTOR.getUnit();
        String[] data = s.split(unit.getUnit());
        DataReading dataReading = new DataReading(Float.parseFloat(data[0]), unit);
        if (list == null) {
            list = new ArrayList<>();
            list.add(dataReading);
            sensorToData.put(Sensor.PHOTOTRANSISTOR, list);
        } else {
            list.add(dataReading);
        }
    }

    private void addSoundReading(String s) {
        List<DataReading> list = sensorToData.get(Sensor.SOUND);
        String[] data = s.split(Sensor.SOUND.getUnit().getUnit());
        DataReading dataReading = new DataReading(Float.parseFloat(data[0]), Unit.DB);
        if (list == null) {
            list = new ArrayList<>();
            list.add(dataReading);
            sensorToData.put(Sensor.SOUND, list);
        } else {
            list.add(dataReading);
        }
    }

    private void addHeartReading(String s) {
        List<DataReading> list = sensorToData.get(Sensor.HR);
        String[] data = s.split(Sensor.HR.getUnit().getUnit());
        DataReading dataReading = new DataReading(Float.parseFloat(data[0]), Unit.BPM);
        if (list == null) {
            list = new ArrayList<>();
            list.add(dataReading);
            sensorToData.put(Sensor.HR, list);
        } else {
            list.add(dataReading);
        }
    }

    private void addHumidityReading(String s) {
        List<DataReading> list = sensorToData.get(Sensor.HUMIDITY);
        String[] data = s.split(Sensor.HUMIDITY.getUnit().getUnit());
        DataReading dataReading = new DataReading(Float.parseFloat(data[0]), Unit.PERCENT);
        if (list == null) {
            list = new ArrayList<>();
            list.add(dataReading);
            sensorToData.put(Sensor.HUMIDITY, list);
        } else {
            list.add(dataReading);
        }
    }

    private void addTemperatureReading(String s) {
        List<DataReading> list = sensorToData.get(Sensor.TEMPERATURE);
        String[] data = s.split(Sensor.TEMPERATURE.getUnit().getUnit());
        DataReading dataReading = new DataReading(Float.parseFloat(data[0]), Unit.C);
        if (list == null) {
            list = new ArrayList<>();
            list.add(dataReading);
            sensorToData.put(Sensor.TEMPERATURE, list);
        } else {
            list.add(dataReading);
        }
    }


}

