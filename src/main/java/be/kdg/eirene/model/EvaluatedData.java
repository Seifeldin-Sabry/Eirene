package be.kdg.eirene.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class EvaluatedData {
	private String signal;
	private String brainwaveStrength;
	private String heartRate;
	private String environment;
	private String generalAdvice;
}
