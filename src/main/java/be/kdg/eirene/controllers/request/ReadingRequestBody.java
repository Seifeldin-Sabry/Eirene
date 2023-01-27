package be.kdg.eirene.controllers.request;

import be.kdg.eirene.model.BrainWaveReading;
import be.kdg.eirene.model.SensorData;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class ReadingRequestBody {
	private Timestamp timestamp;
	private BrainWaveReading brainWave;
	private SensorData sensorData;
}
