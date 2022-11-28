package be.kdg.eirene.service;

import be.kdg.eirene.model.EvaluatedData;
import be.kdg.eirene.model.Reading;
import be.kdg.eirene.model.SessionType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluatorServiceImpl implements EvaluatorService {
	final static int MAX_HEART_RATE = 200;
	final static int MIN_HEART_RATE = 40;
	final static int HEALTHY_HEART_RATE_THRESHOLD = 100;
	final static int GOOD_BRAINWAVE_STRENGTH_THRESHOLD = 50;
	final static int BAD_SIGNAL_STRENGTH_THRESHOLD = 100;
	final static int MAX_SESSION_QUALITY_LEVEL = 5;

	/**
	 This method evaluates the data of a session and returns an EvaluatedData object

	 @param toEvaluate the data to evaluate
	 @param type       the type of session

	 @return an EvaluatedData object
	 */
	@Override
	public EvaluatedData formulateReport(List<Reading> toEvaluate, SessionType type) {
		if (toEvaluate.size() < 10) {
			EvaluatedData calibrating = new EvaluatedData();
			calibrating.setBrainwaveStrength("Calibrating...");
			calibrating.setSignal("Calibrating...");
			calibrating.setHeartRate("Calibrating...");
			calibrating.setGeneralAdvice("Calibrating...");
			calibrating.setEnvironment("Calibrating...");
			return calibrating;
		}
		List<Reading> pastReadings = toEvaluate.subList(toEvaluate.size() - 10, toEvaluate.size());

		// Calculate the average values

		double heartRateValue = pastReadings.parallelStream()
		                                    .mapToDouble(a -> a.getSensorData().getHeartRate().getValue())
		                                    .average()
		                                    .orElse(0);

		double brainwaveStrength = switch (type) {
			case FOCUS -> pastReadings.parallelStream()
			                          .mapToInt(a -> a.getBrainWave().getFocus())
			                          .average()
			                          .orElse(0);
			case MEDITATION -> pastReadings.parallelStream()
			                               .mapToInt(a -> a.getBrainWave().getMeditation())
			                               .average()
			                               .orElse(0);
		};
		double signalStrength = pastReadings.parallelStream()
		                                    .mapToDouble(a -> a.getBrainWave().getSignal())
		                                    .average()
		                                    .orElse(0);
		double humidity = pastReadings.parallelStream()
		                              .mapToDouble(a -> a.getSensorData().getHumidity().getValue())
		                              .average()
		                              .orElse(0);
		double temperature = pastReadings.parallelStream()
		                                 .mapToDouble(a -> a.getSensorData().getTemperature().getValue())
		                                 .average()
		                                 .orElse(0);
		double light = pastReadings.parallelStream()
		                           .mapToDouble(a -> a.getSensorData().getLight().getValue())
		                           .average()
		                           .orElse(0);
		double sound = pastReadings.parallelStream()
		                           .mapToDouble(a -> a.getSensorData().getSound().getValue())
		                           .average()
		                           .orElse(0);

		// formulate the report

		EvaluatedData report = new EvaluatedData();
		report.setHeartRate(evaluateHeartRate(heartRateValue));
		report.setBrainwaveStrength(evaluateBrainwaves(brainwaveStrength));
		report.setSignal(evaluateSignal(signalStrength));
		report.setEnvironment(evaluateEnvironment(humidity, temperature, sound, light));
		report.setGeneralAdvice(evaluateGeneralAdvice(report));
		return report;

	}

	/**
	 Evaluate general advice based on the other evaluations

	 @param report the report to evaluate

	 @return the general advice
	 */
	private String evaluateGeneralAdvice(EvaluatedData report) {
		int qualityLevel = MAX_SESSION_QUALITY_LEVEL;
		if (report.getHeartRate().equals("High")) {
			qualityLevel--;
		}
		if (report.getHeartRate().equals("Low")) {
			qualityLevel--;
		}
		if (report.getBrainwaveStrength().equals("Low")) {
			qualityLevel--;
		}
		if (report.getSignal().equals("Bad")) {
			qualityLevel--;
		}
		if (report.getEnvironment().equals("Bad")) {
			qualityLevel--;
		}
		switch (qualityLevel) {
			case 5 -> {return "Excellent";}
			case 4 -> {return "Good";}
			case 3 -> {return "Fair";}
			case 2 -> {return "Poor";}
			case 1 -> {return "Bad";}
			case 0 -> {return "Terrible";}
			default -> {return "Unknown";}
		}
	}

	/**
	 Evaluate the heart rate

	 @param value the heart rate value

	 @return the evaluation
	 */
	private String evaluateHeartRate(double value) {
		StringBuilder report = new StringBuilder();
		report.append(value).append(" BPM ");
		if (value > MAX_HEART_RATE || value < MIN_HEART_RATE) {
			report.append("| Deadly heart rate!");
		} else if (value >= MIN_HEART_RATE && value <= HEALTHY_HEART_RATE_THRESHOLD) {
			report.append("| Good heart rate!");
		} else if (value > HEALTHY_HEART_RATE_THRESHOLD) {
			report.append("| Calm down");
		}
		return report.toString();
	}

	/**
	 Evaluate the brainwave strength

	 @param brainwaveStrength the brainwave strength value

	 @return the evaluation
	 */
	private String evaluateBrainwaves(double brainwaveStrength) {
		return brainwaveStrength > GOOD_BRAINWAVE_STRENGTH_THRESHOLD ? "High" : "Low";
	}

	/**
	 Evaluate the signal strength

	 @param signalStrength the signal strength value

	 @return the evaluation
	 */
	private String evaluateSignal(double signalStrength) {
		return signalStrength > BAD_SIGNAL_STRENGTH_THRESHOLD ? "Bad" : "Good";
	}


	/**
	 Evaluate the environment

	 @param humidity    the humidity value
	 @param temperature the temperature value
	 @param light       the light value

	 @return the evaluation
	 */
	private String evaluateEnvironment(double humidity, double temperature, double sound, double light) {
		// TODO: god only knows what the sound and light value represent, please someone save us
		int roomQuality = 0;
		if (humidity >= 30 && humidity <= 50) {
			roomQuality++;
		}
		switch (roomQuality) {
			case 0 -> {return "Bad";}
			case 1 -> {return "Average";}
			case 2 -> {return "Good";}
			case 3 -> {return "Excellent";}
			default -> {return "No Data";}
		}

	}
}
