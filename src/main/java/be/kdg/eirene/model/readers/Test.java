package be.kdg.eirene.model.readers;

import jssc.SerialPortList;

import java.util.Arrays;
import java.util.HashSet;

public class Test {
	public static void main(String[] args) throws PortNotFoundException {
		final HashSet<String> ports = new HashSet<>(Arrays.asList(SerialPortList.getPortNames()));
		System.out.println(ports);

		Connector connector = new Connector();
		connector.connectAll();
	}
}
