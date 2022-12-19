package be.kdg.eirene.repository;

import lombok.Getter;
import org.thymeleaf.util.StringUtils;

@Getter
public enum GlobalAnalCategory {
	TERRIBLE(50),
	BAD(60),
	OK(70),
	GOOD(80),
	GREAT(90),
	EXCELLENT(100);

	private final int percentile;

	GlobalAnalCategory(int percentile) {
		this.percentile = percentile;
	}

	public String getCapitalizedName() {
		return StringUtils.capitalize(this.name().toLowerCase());
	}
}
