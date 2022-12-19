package be.kdg.eirene.util;

import be.kdg.eirene.model.BrainWaveReading;
import be.kdg.eirene.model.Reading;
import be.kdg.eirene.model.SensorData;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReadingsAdaptor implements ReadingsAdapt {
	private final int timestampIndex = 0;
	private final int heartRateIndex = 1;
	private final int humidityIndex = 2;
	private final int lightIndex = 3;
	private final int soundIndex = 4;
	private final int temperatureIndex = 5;
	private final int focusIndex = 6;
	private final int meditationIndex = 7;
	private final int signalIndex = 8;

	@Override
	public List<Reading> convert(List<Object[]> readings) {
		List<Reading> readingsToReturn = new ArrayList<>();
		for (Object[] reading : readings) {
			readingsToReturn.add(
					new Reading((Timestamp) reading[timestampIndex],
							new BrainWaveReading(Integer.parseInt(reading[signalIndex].toString()),
									Integer.parseInt(reading[focusIndex].toString()),
									Integer.parseInt(reading[meditationIndex].toString())),
							new SensorData(Float.parseFloat(reading[heartRateIndex].toString()),
									Float.parseFloat(reading[temperatureIndex].toString()),
									Float.parseFloat(reading[soundIndex].toString()),
									Float.parseFloat(reading[humidityIndex].toString()),
									Float.parseFloat(reading[lightIndex].toString()))));
		}
		return readingsToReturn;
	}
}
