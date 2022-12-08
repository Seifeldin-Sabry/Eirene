package be.kdg.eirene.service.evaluator;

import be.kdg.eirene.model.EvaluatedData;

public interface EvaluatorService {
	String evaluateGeneralAdvice(EvaluatedData report);

	String evaluateHeartRate(double value);

	String evaluateBrainwaves(double brainwaveStrength);

	String evaluateSignal(double signalStrength);

	String evaluateEnvironment(double humidity, double temperature, double sound, double light);

	boolean isHumidityGood(double humidity);

	boolean isTemperatureGood(double temperature);

	boolean isLightSpike(double light);
}
