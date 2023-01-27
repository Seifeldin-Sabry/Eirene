package be.kdg.eirene.controllers.request.encryptedObjects;

import lombok.Getter;

@Getter
public class EncryptedSensorData {
	private EncryptedSensorReading heartRate;
	private EncryptedSensorReading temperature;
	private EncryptedSensorReading sound;
	private EncryptedSensorReading humidity;
	private EncryptedSensorReading light;
}
