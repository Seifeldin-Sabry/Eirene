package be.kdg.eirene.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EvaluatedData {
	private String signal;
	private String brainwaveStrength;
	private String heartRate;
	private String environment;
	private String generalAdvice;

	public EvaluatedData() {
		this.signal = "Calibrating...";
		this.brainwaveStrength = "Calibrating...";
		this.heartRate = "Calibrating...";
		this.environment = "Calibrating...";
		this.generalAdvice = "Calibrating...";
	}
}
