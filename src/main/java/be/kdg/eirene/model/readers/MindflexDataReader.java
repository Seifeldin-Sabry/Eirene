package be.kdg.eirene.model.readers;

import jssc.SerialPort;
import jssc.SerialPortException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;


public class MindflexDataReader extends Reader {
	public MindflexDataReader(Reading reading) {
		super(reading);
	}

	@Override
	public Optional<String> findPort() {
		HashSet<String> ports = new HashSet<>(getAllPorts());
		if (ports.isEmpty()) return Optional.empty();

		ArrayList<SerialPort> serialPorts = new ArrayList<>();
		ports.forEach(port -> startEventListener(serialPorts, new PacketParser(), port));

		findPortNameOrTimeout();

		serialPorts.forEach(this::removeEventListenerAndClosePort);
		return Optional.ofNullable(getPortName());
	}


	@Override
	void connect(String port) throws PortNotFoundException {
		try {
			PacketParser packetParser = new PacketParser();
			SerialPort serialPort = new SerialPort(port);
			serialPort.openPort();
			serialPort.setParams(
					SerialPort.BAUDRATE_9600,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE
			);
			serialPort.addEventListener(event -> {
				try {
					packetParser.readPacket(serialPort, getReading());
					getReading().logData();
				} catch (SerialPortException e) {
					throw new RuntimeException(e);
				}
			});
		} catch (SerialPortException e) {
			throw new PortNotFoundException("Failed to connect to port " + port);
		}
	}

	private void startEventListener(ArrayList<SerialPort> serialPorts, PacketParser packetParser, String port) {
		AtomicInteger numberOfTries = new AtomicInteger(0);
		SerialPort serialPort = new SerialPort(port);
		serialPorts.add(serialPort);
		AtomicInteger packetCounter = new AtomicInteger();
		List<Integer> packet = new ArrayList<>();
		try {
			serialPort.openPort();
			serialPort.setParams(
					SerialPort.BAUDRATE_9600,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE
			);
			serialPort.addEventListener(event -> {
				if (numberOfTries.get() > MAX_TRIES) {
					System.out.println("REACHED MAX " + port);
					removeEventListenerAndClosePort(serialPort);
				} else numberOfTries.incrementAndGet();

				if (!event.isRXCHAR()) return;
				try {
					packet.add(serialPort.readIntArray(1)[0]);
					packetCounter.getAndIncrement();
					if (packetParser.isDataByte(packet)) {
						System.out.println("Port found: " + port);
						setSerialPort(port);
						removeEventListenerAndClosePort(serialPort);
					}
				} catch (SerialPortException e) {
					System.out.println(e.getMessage());
					removeEventListenerAndClosePort(serialPort);
				}
			});
		} catch (SerialPortException e) {
			removeEventListenerAndClosePort(serialPort);
			serialPorts.remove(serialPort);
			System.out.println("MindFlex port not found: " + port);
		}
	}
}
