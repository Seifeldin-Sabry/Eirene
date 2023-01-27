package be.kdg.eirene.util;

import be.kdg.eirene.controllers.request.ReadingRequestBody;
import be.kdg.eirene.controllers.request.encryptedObjects.EncryptedReadingRequestBody;
import be.kdg.eirene.model.BrainWaveReading;
import be.kdg.eirene.model.SensorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestDecryptor {
	private final AEService aes;

	@Autowired
	public RequestDecryptor(AEService aes) {
		this.aes = aes;
	}

	public ReadingRequestBody decrypt(EncryptedReadingRequestBody encrypted) {
		ReadingRequestBody decrypted = new ReadingRequestBody();
		decrypted.setTimestamp(encrypted.getTimestamp());
		decrypted.setBrainWave(new BrainWaveReading(Integer.parseInt(aes.decrypt(encrypted.getReading()
		                                                                                  .getSignal())), Integer.parseInt(aes.decrypt(encrypted.getReading()
		                                                                                                                                        .getFocus())), Integer.parseInt(aes.decrypt(encrypted.getReading()
		                                                                                                                                                                                             .getMeditation()))));
		decrypted.setSensorData(
				new SensorData(
						Float.parseFloat(aes.decrypt(encrypted.getSensorData().getHeartRate().getValue())),
						Float.parseFloat(aes.decrypt(encrypted.getSensorData().getTemperature().getValue())),
						Float.parseFloat(aes.decrypt(encrypted.getSensorData().getSound().getValue())),
						Float.parseFloat(aes.decrypt(encrypted.getSensorData().getHumidity().getValue())),
						Float.parseFloat(aes.decrypt(encrypted.getSensorData().getLight().getValue()))));
		return decrypted;
	}
}
