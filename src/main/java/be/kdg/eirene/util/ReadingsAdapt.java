package be.kdg.eirene.util;

import be.kdg.eirene.model.Reading;

import java.util.List;

public interface ReadingsAdapt {
	int timestampIndex = 0;
	int heartRateIndex = 1;
	int humidityIndex = 2;
	int lightIndex = 3;
	int soundIndex = 4;
	int temperatureIndex = 5;
	int focusIndex = 6;
	int meditationIndex = 7;
	int signalIndex = 8;

	List<Reading> convertToReadings();
}
