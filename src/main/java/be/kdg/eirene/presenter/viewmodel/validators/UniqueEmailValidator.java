package be.kdg.eirene.presenter.viewmodel.validators;

import be.kdg.eirene.presenter.viewmodel.constraints.UniqueEmailConstraint;
import be.kdg.eirene.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.logging.Logger;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmailConstraint, String> {

	private final UserRepository userRepository;
	Logger logger = Logger.getLogger(UniqueEmailValidator.class.getName());

	@Autowired
	public UniqueEmailValidator(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void initialize(UniqueEmailConstraint constraintAnnotation) {
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		return !userRepository.existsByEmailIgnoreCase(email);
	}
}
