package be.kdg.eirene.model;

import lombok.Getter;

@Getter
public enum SatisfactionLevel {
	VERY_SATISFIED("bi-emoji-heart-eyes", 5),
	SATISFIED("bi-emoji-smile", 4),
	NEUTRAL("bi-emoji-expressionless", 3),
	DISSATISFIED("bi-emoji-frown", 2),
	VERY_DISSATISFIED("bi-emoji-dizzy", 1);


	private final String biClass;
	private final int value;

	SatisfactionLevel(String biClass, int value) {
		this.biClass = biClass;
		this.value = value;
	}
}
