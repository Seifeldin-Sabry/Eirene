package be.kdg.eirene.model.readers;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@Getter
@Setter
public abstract class Reader {

    private final int MAX_DURATION = 60;
    private final int MAX_TRIES = 50;
    private SerialPort serialPort;
    private String portName;

    private DataStream dataStream;

    public Reader(DataStream dataStream) {
        this.dataStream = dataStream;
    }

    public abstract Optional<String> findPort();

    public boolean isMacOS() {
        return getAllPorts().stream().anyMatch(portName -> portName.contains("dev"));
    }

    abstract void connect(String port) throws PortNotFoundException;

    public SerialPort getSerialPort() {
        return serialPort;
    }

    void setSerialPort(String targetPortName) {
        this.portName = targetPortName;
        this.serialPort = new SerialPort(portName);
    }

    public void removeEventListenerAndClosePort(SerialPort serialPort) {
        try {
            serialPort.removeEventListener();
            serialPort.closePort();
        } catch (SerialPortException ignored) {
        }
    }

    public HashSet<String> getAllPorts() {
        return new HashSet<>(Arrays.asList(SerialPortList.getPortNames()));
    }

}
