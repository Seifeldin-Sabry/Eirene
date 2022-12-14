package be.kdg.eirene.model;

import org.thymeleaf.util.StringUtils;

public enum SessionType {
	FOCUS,
	MEDITATION;

	public String getCapitalizedName() {
		return StringUtils.capitalize(this.name().toLowerCase());
	}
}
