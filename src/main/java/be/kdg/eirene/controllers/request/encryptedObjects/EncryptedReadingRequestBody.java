package be.kdg.eirene.controllers.request.encryptedObjects;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class EncryptedReadingRequestBody {
	private Timestamp timestamp;
	private EncryptedBrainwaveReading reading;
	private EncryptedSensorData sensorData;
}
