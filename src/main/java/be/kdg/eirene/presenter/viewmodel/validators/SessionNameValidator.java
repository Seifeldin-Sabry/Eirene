package be.kdg.eirene.presenter.viewmodel.validators;

import be.kdg.eirene.presenter.viewmodel.constraints.SessionNameConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Random;

public class SessionNameValidator implements ConstraintValidator<SessionNameConstraint, String> {
	private final Random random = new Random();
	private final int SESSION_NAME_LENGTH = 50;
	String sessionName;

	@Override
	public void initialize(SessionNameConstraint constraintAnnotation) {
	}

	@Override
	public boolean isValid(String sessionName, ConstraintValidatorContext context) {
		if (sessionName.isBlank() || sessionName.isEmpty()) this.sessionName = setRandomSessionName();
		return true;
	}

	private String setRandomSessionName() {
		StringBuilder key = new StringBuilder();
		for (int i = 0; i < SESSION_NAME_LENGTH; i++) {
			key.append((char) (random.nextInt(127 - 32) + 32));
		}
		return key.toString();
	}
}
