package be.kdg.eirene.model.readers;

import jssc.SerialPort;
import jssc.SerialPortException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PacketParser {
	final static int PACKET_SIZE_BYTES = 36;
	final static int SYNC_BYTE = 170;

	boolean isDataByte(List<Integer> packet) {
		return packet.size() == PACKET_SIZE_BYTES && packet.get(0) == SYNC_BYTE && packet.get(1) == SYNC_BYTE;
	}

	/**
	 Parse a packet into a map of headers and values

	 @param packet a list of bytes

	 @return a map of headers and values
	 */
	Map<String, Integer> parse(List<Integer> packet) {
		String[] headers = { "SYNC", "SYNC", "PLENGTH", "SIGNAL CODE", "SIGNAL VALUE",
				"ASIC_EEG_POWER_INT", "VLENGTH", "DELTA START", "DELTA", "DELTA END", "THETA START", "THETA", "THETA END",
				"LOWALPHA START", "LOWALPHA", "LOWALPHA END", "HIGHALPHA START", "HIGHALPHA", "HIGHALPHA END",
				"LOWBETA START", "LOWBETA", "LOWBETA END", "HIGHBETA START", "HIGHBETA", "HIGHBETA END",
				"LOWGAMMA START", "LOWGAMMA", "LOWGAMMA END", "MIDGAMMA START", "MIDGAMMA", "MIDGAMMA END", "ATTENTION CODE",
				"ATTENTION VALUE", "MEDITATION CODE", "MEDITATION VALUE", "CHECKSUM" };
		Map<String, Integer> parsedData = new LinkedHashMap<>();
		for (int i = 0; i < headers.length; i++) {
			try {
				parsedData.put(headers[i], packet.get(i));
			} catch (IndexOutOfBoundsException e) {
				System.out.println("ERROR IN PACKET PARSER: " + e);
				parsedData.put(headers[i], null);
			}
		}
		return parsedData;
	}

	/**
	 Reads a packet from the serial port and updates the data stream with the data read from the packet.

	 @param serialPort

	 @return true if the packet is a Mindflex packet, false if not.

	 @throws SerialPortException
	 */
	public boolean readPacket(SerialPort serialPort, Reading reading) throws SerialPortException {
		PacketParser parser = new PacketParser();
		try {
			List<Integer> packet = new ArrayList<>();
			//check if packet is complete
			while (!parser.isDataByte(packet)) {
				int[] buffer1 = serialPort.readIntArray(1); // read byte from the headset  <---- PROBLEM HERE
				packet.add(buffer1[0]);
				if (packet.size() > 37) {
					System.out.println("Packet too long for " + serialPort.getPortName());
					return false;
				}
			}

			Map<String, Integer> data = parser.parse(packet);

			// Setting the data
			reading.setBrainWave(new BrainWaveReading(data.get("SIGNAL VALUE"), data.get("ATTENTION VALUE"), data.get("MEDITATION VALUE")));
			reading.setTimestamp(new Timestamp(System.currentTimeMillis()));
			return true;

		} catch (SerialPortException e) {
			System.out.println("Error reading packet from port: " + serialPort.getPortName());
			throw e;
		}
	}
}
