package be.kdg.eirene.util;

import be.kdg.eirene.model.Reading;
import org.springframework.stereotype.Service;

@Service
public class ReadingValidator {
	final static int MAX_HEART_RATE = 200;
	final static int MIN_HEART_RATE = 20;

	/**
	 Validate the data

	 @param reading the reading to validate

	 @return true if the data is valid, false otherwise
	 */
	public boolean validate(Reading reading) {

		// Heart rate

		// Check if the reading is not null
		if (reading.getSensorData() == null) {
			return false;
		}
		// Check if the bpm reading is within boundaries
		return reading.getSensorData()
		                .getHeartRate() > MIN_HEART_RATE && reading.getSensorData()
		                                                              .getHeartRate() < MAX_HEART_RATE;
	}
}
