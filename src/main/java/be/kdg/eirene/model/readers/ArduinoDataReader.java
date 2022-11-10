package be.kdg.eirene.model.readers;

import jssc.SerialPort;
import jssc.SerialPortException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
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
	private final String packetPattern = String.format("(.*)?\\d+%s \\d+%s \\d+%s \\d+\\.?\\d+%s \\d+\\.?\\d+%s(.*)?",
			Unit.L.getUnit(),
			Unit.BPM.getUnit(),
			Unit.DB.getUnit(),
			Unit.C.getUnit(),
			Unit.PERCENT.getUnit());
	private final SensorData sensorData;

	public ArduinoDataReader(Reading reading) {
		super(reading);
		sensorData = new SensorData();
	}

	@Override
	public Optional<String> findPort() {
		HashSet<String> ports = new HashSet<>(getAllPorts());
		if (ports.isEmpty()) return Optional.empty();

		ArrayList<SerialPort> serialPorts = new ArrayList<>();
		ports.forEach(port -> startEventListener(serialPorts, port));

		findPortNameOrTimeout();

		serialPorts.forEach(this::removeEventListenerAndClosePort);
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
				if (numberOfTries.get() == MAX_TRIES) {
					System.out.println("REACHED MAX " + port);
					removeEventListenerAndClosePort(serialPort);
					return;
				} else numberOfTries.incrementAndGet();

				if (!event.isRXCHAR()) return;
				String dataIncoming;
				try {
					dataIncoming = serialPort.readString();
				} catch (SerialPortException | NullPointerException e) {
					System.out.println(e.getMessage());
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
						int newPacketIndex = dataIncoming.indexOf("%") + 1;
						String beforePercent = dataIncoming.substring(0, newPacketIndex);
						data = currentReading.get().append(beforePercent).toString();
						String afterPercent = dataIncoming.substring(newPacketIndex);
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
				saveReading();
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
	void connect(String port) {
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
					String s;
					try {
						s = serialPort.readString();

						if (s.contains("%")) {
							parse(currentReading, s);
							return;
						}
						currentReading.get().append(s);
						saveReading();

					} catch (NullPointerException e) {
						System.out.println("ERROR IN ARDUINO DATA READER: " + e);
					} catch (SerialPortException e) {
						System.out.println("Error while reading Arduino data " + e);
					}
				}
			});

		} catch (SerialPortException e) {
			e.printStackTrace();
		}
	}

	private void parse(AtomicReference<StringBuilder> currentReading, String s) {
		String[] data;
		if ("%".equals(s)) {
			data = currentReading.get().append("%").toString().split(" ");
			processData(data);
			currentReading.set(new StringBuilder());
			return;
		}
		if (s.endsWith("%") && s.length() > 1) {
			data = currentReading.get().append(s).toString().split(" ");
			processData(data);
			currentReading.set(new StringBuilder());
			return;
		}
		int newPacketIndex = s.indexOf("%") + 1;
		String beforePercent = s.substring(0, newPacketIndex);
		data = currentReading.get().append(beforePercent).toString().split(" ");
		processData(data);
		String afterPercent = s.substring(newPacketIndex);
		currentReading.set(new StringBuilder(afterPercent));
	}

	/**
	 Combines data that is split up into multiple packets into one packet, updates the data stream
	 */
	private void saveReading() {
		getReading().setSensorData(sensorData);
		getReading().logData();
	}

	private void processData(String[] data) {
		for (String s : data) {
			if (Pattern.matches(temperatureMatching, s)) addReading(s, Unit.C);
			else if (Pattern.matches(humidityMatching, s)) addReading(s, Unit.PERCENT);
			else if (Pattern.matches(heartMatching, s)) addReading(s, Unit.BPM);
			else if (Pattern.matches(soundMatching, s)) addReading(s, Unit.DB);
			else if (Pattern.matches(lightMatching, s)) addReading(s, Unit.L);
		}
	}

	private void addReading(String s, Unit unit) {
		String[] data = s.split(unit.getUnit());
		SensorReading dataReading = new SensorReading(Float.parseFloat(data[0]), unit);
		switch (unit) {
			case L -> sensorData.setLight(dataReading);
			case PERCENT -> sensorData.setHumidity(dataReading);
			case C -> sensorData.setTemperature(dataReading);
			case DB -> sensorData.setSound(dataReading);
			case BPM -> sensorData.setHeartRate(dataReading);
		}
	}
}
