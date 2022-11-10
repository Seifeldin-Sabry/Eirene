package be.kdg.eirene.model.readers;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@Getter
@Setter
public abstract class Reader {

	final int MAX_DURATION = 60;
	final int MAX_TRIES = 50;
	private SerialPort serialPort;
	private String portName;

	private Reading reading;

	public Reader(Reading reading) {
		this.reading = reading;
	}

	public abstract Optional<String> findPort();

	private boolean isMacOS() {
		return getAllPorts().stream().anyMatch(portName -> portName.contains("dev"));
	}

	abstract void connect(String port) throws PortNotFoundException;

	void setSerialPort(String targetPortName) {
		this.portName = targetPortName;
		this.serialPort = new SerialPort(portName);
	}

	public void removeEventListenerAndClosePort(SerialPort serialPort) {
		try {
			serialPort.removeEventListener();
			serialPort.closePort();
		} catch (SerialPortException e) {
			System.out.printf("Problem while closing port %s and removing its event listener %s\n", e.getPort(), e);
			e.printStackTrace();
		}
	}

	void findPortNameOrTimeout() {
		boolean timePassed = false;
		LocalDateTime startTime = LocalDateTime.now();
		while (getPortName() == null && !timePassed) {
			LocalDateTime now = LocalDateTime.now();
			long secondsPassed = ChronoUnit.SECONDS.between(startTime, now);
			timePassed = secondsPassed > MAX_DURATION;
		}
	}

	public HashSet<String> getAllPorts() {
		return isMacOS() ? new HashSet<>(getPorts()
				.parallelStream()
				.filter(s -> s.contains("tty")).toList()) : getPorts();
	}

	private HashSet<String> getPorts() {
		return new HashSet<>(Arrays.asList(SerialPortList.getPortNames()));
	}

}
