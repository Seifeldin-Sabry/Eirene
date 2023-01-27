package be.kdg.eirene.util;

import be.kdg.eirene.model.BrainWaveReading;
import be.kdg.eirene.model.Reading;
import be.kdg.eirene.model.SensorData;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReadingsObjectAdaptor implements ReadingsAdapt {

	private final List<Object[]> readings;

	public ReadingsObjectAdaptor(List<Object[]> readings) {
		this.readings = readings;
	}

	@Override
	public List<Reading> convertToReadings() {
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
