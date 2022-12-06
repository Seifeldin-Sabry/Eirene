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
	private final int temperatureIndex = 2;
	private final int soundIndex = 3;
	private final int humidityIndex = 4;
	private final int lightIndex = 5;
	private final int signalIndex = 8;
	private final int focusIndex = 6;
	private final int meditationIndex = 7;

	@Override
	public List<Reading> convert(List<Object[]> readings) {
		List<Reading> readingsToReturn = new ArrayList<>();
		for (Object[] reading : readings) {
			readingsToReturn.add(new Reading((Timestamp) reading[timestampIndex], new BrainWaveReading((int) reading[signalIndex], (int) reading[focusIndex], (int) reading[meditationIndex]), new SensorData((float) reading[heartRateIndex], (float) reading[temperatureIndex], (float) reading[soundIndex], (float) reading[humidityIndex], (float) reading[lightIndex])));
		}
		return readingsToReturn;
	}
}
