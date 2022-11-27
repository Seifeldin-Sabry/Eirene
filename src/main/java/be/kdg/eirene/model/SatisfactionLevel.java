package be.kdg.eirene.model;

import lombok.Getter;

@Getter
public enum SatisfactionLevel {
	VERY_SATISFIED("https://via.placeholder.com/40x60/0bf/fff&text=A", 5),
	SATISFIED("https://via.placeholder.com/40x60/0f0/fff&text=B", 4),
	NEUTRAL("https://via.placeholder.com/40x60/ff0/fff&text=C", 3),
	DISSATISFIED("https://via.placeholder.com/40x60/f80/fff&text=D", 2),
	VERY_DISSATISFIED("https://via.placeholder.com/40x60/f00/fff&text=E", 1);


	private final String imageSrc;
	private final int value;

	SatisfactionLevel(String imageSrc, int value) {
		this.imageSrc = imageSrc;
		this.value = value;
	}
}
