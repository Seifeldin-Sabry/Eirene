package be.kdg.eirene.presenter.viewmodel.validators.password;

import be.kdg.eirene.presenter.viewmodel.PasswordMatcher;
import be.kdg.eirene.presenter.viewmodel.constraints.PasswordMatchConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatchConstraint, PasswordMatcher> {

	@Override
	public void initialize(PasswordMatchConstraint constraintAnnotation) {
	}

	@Override
	public boolean isValid(PasswordMatcher viewModel, ConstraintValidatorContext context) {
		return viewModel.passwordsMatch();
	}
}
