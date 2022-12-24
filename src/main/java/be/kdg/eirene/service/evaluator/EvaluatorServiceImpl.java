package be.kdg.eirene.service.evaluator;

import be.kdg.eirene.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class EvaluatorServiceImpl implements EvaluatorService {
	final static int MIN_HEART_RATE = 40;
	final static int HEALTHY_HEART_RATE_THRESHOLD = 100;
	final static int MIN_GOOD_HUMIDITY = 30;
	final static int MAX_GOOD_HUMIDITY = 50;
	final static int MIN_GOOD_TEMP = 20;
	final static int MAX_GOOD_TEMP = 25;
	final static int LIGHT_SPIKE_THRESHOLD = 30;
	final static int GOOD_BRAINWAVE_STRENGTH_THRESHOLD = 50;
	final static int BAD_SIGNAL_STRENGTH_THRESHOLD = 100;
	final static int MAX_SESSION_QUALITY_LEVEL = 4;
	final static int MAX_ROOM_QUALITY_LEVEL = 3;


	/**
	 Evaluate general advice based on the other evaluations

	 @param report the report to evaluate

	 @return the general advice
	 */
	public String evaluateGeneralAdvice(EvaluatedData report) {
		int qualityLevel = MAX_SESSION_QUALITY_LEVEL;
		if (report.getHeartRateValue() > HEALTHY_HEART_RATE_THRESHOLD) {
			qualityLevel--;
		}
		if (report.getBrainwaveStrengthValue() < GOOD_BRAINWAVE_STRENGTH_THRESHOLD) {
			qualityLevel--;
		}
		if (report.getSignal().equals("Bad")) {
			qualityLevel--;
		}
		if (report.getEnvironment().equals("Bad")) {
			qualityLevel--;
		}
		switch (qualityLevel) {
			case 4 -> {return "Excellent";}
			case 3 -> {return "Good";}
			case 2 -> {return "Average";}
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
	public String evaluateHeartRate(double value) {
		StringBuilder report = new StringBuilder();
		report.append(value).append(" BPM ");
		if (value >= MIN_HEART_RATE && value <= HEALTHY_HEART_RATE_THRESHOLD) {
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
	public String evaluateBrainwaves(double brainwaveStrength) {
		return brainwaveStrength > GOOD_BRAINWAVE_STRENGTH_THRESHOLD ? "High" : "Low";
	}

	/**
	 Evaluate the signal strength

	 @param signalStrength the signal strength value

	 @return the evaluation
	 */
	public String evaluateSignal(double signalStrength) {
		return signalStrength > BAD_SIGNAL_STRENGTH_THRESHOLD ? "Bad" : "Good";
	}


	/**
	 Evaluate the environment

	 @param humidity    the humidity value
	 @param temperature the temperature value
	 @param light       the light value

	 @return the evaluation
	 */
	public String evaluateEnvironment(double humidity, double temperature, double sound, double light) {
		int roomQuality = MAX_ROOM_QUALITY_LEVEL;
		if (!isHumidityGood(humidity)) {
			roomQuality--;
		}
		if (!isTemperatureGood(temperature)) {
			roomQuality--;
		}
		if (isLightSpike(light)) {
			roomQuality--;
		}

		switch (roomQuality) {
			case 3 -> {return "Excellent";}
			case 2 -> {return "Good";}
			case 1 -> {return "Average";}
			case 0 -> {return "Bad";}
			default -> {return "Unknown";}
		}

	}

	private double getAverage(List<Reading> readings, Sensor sensor) {
		return readings.stream()
		               .mapToDouble(reading -> reading.getSensorData().getDataOf(sensor))
		               .average()
		               .getAsDouble();
	}

	public String evaluateRecentReadings(Session session) {
		final int RECENT_READINGS_THRESHOLD = 5;
		final int BRAINWAVE_STRENGTH_BUFFER = 10;
		final int HEART_RATE_BUFFER = 10;
		final int LIGHT_BUFFER = 0;
		final int HUMIDITY_BUFFER = 2;
		final int TEMPERATURE_BUFFER = 1;
		final int SOUND_BUFFER = 1;

		final SessionType sessionType = session.getType();
		final List<Reading> readings = session.getReadings();

		if (readings.size() < RECENT_READINGS_THRESHOLD) return null;

		final List<Reading> recentReadings = readings.subList(readings.size() - RECENT_READINGS_THRESHOLD, readings.size());
		final Reading lastReading = readings.get(readings.size() - 1);

		final List<Integer> brainwaveStrengths = getBrainwaveStrengthsByType(recentReadings, sessionType);

		final int averageBrainwaveStrength = getAverageOfIntegers(brainwaveStrengths);
		final int lastBrainwaveStrength = brainwaveStrengths.get(brainwaveStrengths.size() - 1);

		if (lastBrainwaveStrength > averageBrainwaveStrength - BRAINWAVE_STRENGTH_BUFFER) return null;

		final double averageHeartRate = getAverage(recentReadings, Sensor.HR);
		final double averageLight = getAverage(recentReadings, Sensor.PHOTOTRANSISTOR);
		final double averageHumidity = getAverage(recentReadings, Sensor.HUMIDITY);
		final double averageTemperature = getAverage(recentReadings, Sensor.TEMPERATURE);
		final double averageSound = getAverage(recentReadings, Sensor.SOUND);

		final ArrayList<String> causes = new ArrayList<>();

		if (isFactorProblematic(lastReading.getSensorData().getHeartRate(), averageHeartRate, HEART_RATE_BUFFER))
			causes.add("your heart rate");
		if (isFactorProblematic(lastReading.getSensorData().getLight(), averageLight, LIGHT_BUFFER))
			causes.add("the light");
		if (isFactorProblematic(lastReading.getSensorData().getHumidity(), averageHumidity, HUMIDITY_BUFFER))
			causes.add("the humidity");
		if (isFactorProblematic(lastReading.getSensorData().getTemperature(), averageTemperature, TEMPERATURE_BUFFER))
			causes.add("the temperature");
		if (isFactorProblematic(lastReading.getSensorData().getSound(), averageSound, SOUND_BUFFER))
			causes.add("the sound");

		final String cause = String.join(" and ", causes);

		return String.format("Your %s has dropped%s.", sessionType.toString()
		                                                          .toLowerCase(Locale.ROOT), cause.isEmpty() ? "" : " and it might be because of " + cause);
	}

	private List<Integer> getBrainwaveStrengthsByType(List<Reading> readings, SessionType sessionType) {
		return switch (sessionType) {
			case FOCUS -> readings.stream().map(reading -> reading.getBrainWave().getFocus()).toList();
			case MEDITATION -> readings.stream().map(reading -> reading.getBrainWave().getMeditation()).toList();
		};
	}

	private int getAverageOfIntegers(List<Integer> integers) {
		return integers.stream().mapToInt(Integer::intValue).sum() / integers.size();
	}

	private boolean isFactorProblematic(double factor, double average, double buffer) {
		return factor < average - buffer || factor > average + buffer;
	}

	public boolean isHumidityGood(double humidity) {
		return humidity >= MIN_GOOD_HUMIDITY && humidity <= MAX_GOOD_HUMIDITY;
	}

	public boolean isTemperatureGood(double temperature) {
		return temperature >= MIN_GOOD_TEMP && temperature <= MAX_GOOD_TEMP;
	}

	public boolean isLightSpike(double light) {
		return light > LIGHT_SPIKE_THRESHOLD;
	}

}
