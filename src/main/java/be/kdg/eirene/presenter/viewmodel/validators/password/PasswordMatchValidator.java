package be.kdg.eirene.presenter.viewmodel.validators.password;

import be.kdg.eirene.presenter.viewmodel.UserSignUpViewModel;
import be.kdg.eirene.presenter.viewmodel.constraints.PasswordMatchConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatchConstraint, UserSignUpViewModel> {

	@Override
	public void initialize(PasswordMatchConstraint constraintAnnotation) {
	}

	@Override
	public boolean isValid(UserSignUpViewModel userSignUpViewModel, ConstraintValidatorContext context) {
		return userSignUpViewModel.getPassword().equals(userSignUpViewModel.getConfirmPassword());
	}
}
