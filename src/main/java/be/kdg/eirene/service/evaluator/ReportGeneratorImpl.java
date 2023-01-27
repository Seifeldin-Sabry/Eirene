package be.kdg.eirene.service.evaluator;

import be.kdg.eirene.model.EvaluatedData;
import be.kdg.eirene.model.Reading;
import be.kdg.eirene.model.SessionType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportGeneratorImpl implements ReportGeneratorService {
	private final EvaluatorServiceImpl evaluatorServiceImpl;

	public ReportGeneratorImpl(EvaluatorServiceImpl evaluatorServiceImpl) {
		this.evaluatorServiceImpl = evaluatorServiceImpl;
	}

	/**
	 This method evaluates the data of a session and returns an EvaluatedData object

	 @param toEvaluate the data to evaluate
	 @param type       the type of session

	 @return an EvaluatedData object
	 */
	@Override
	public EvaluatedData formulateReport(List<Reading> toEvaluate, SessionType type) {
		if (toEvaluate.size() < 5) {
			return new EvaluatedData();
		}
		Reading mostRecent = toEvaluate.get(toEvaluate.size() - 1);

		// Calculate the average values

		double heartRateValue = mostRecent.getSensorData().getHeartRate();

		double brainwaveStrength = switch (type) {
			case FOCUS -> mostRecent.getBrainWave().getFocus();
			case MEDITATION -> mostRecent.getBrainWave().getMeditation();
		};
		double signalStrength = mostRecent.getBrainWave().getSignal();
		double humidity = mostRecent.getSensorData().getHumidity();
		double temperature = mostRecent.getSensorData().getTemperature();
		double light = mostRecent.getSensorData().getLight();
		double sound = mostRecent.getSensorData().getSound();

		// formulate the report

		EvaluatedData report = new EvaluatedData();
		report.setBrainwaveStrengthValue((int) brainwaveStrength);
		report.setHeartRateValue((int) heartRateValue);
		report.setHeartRate(evaluatorServiceImpl.evaluateHeartRate(heartRateValue));
		report.setBrainwaveStrength(evaluatorServiceImpl.evaluateBrainwaves(brainwaveStrength));
		report.setSignal(evaluatorServiceImpl.evaluateSignal(signalStrength));
		report.setEnvironment(evaluatorServiceImpl.evaluateEnvironment(humidity, temperature, sound, light));
		report.setGeneralAdvice(evaluatorServiceImpl.evaluateGeneralAdvice(report));
		return report;
	}
}
