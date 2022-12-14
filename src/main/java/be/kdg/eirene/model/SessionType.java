package be.kdg.eirene.model;

import org.thymeleaf.util.StringUtils;

public enum SessionType {
	FOCUS,
	MEDITATION;

	@Override
	public String toString() {
		return StringUtils.capitalize(this.name().toLowerCase());
	}
}
