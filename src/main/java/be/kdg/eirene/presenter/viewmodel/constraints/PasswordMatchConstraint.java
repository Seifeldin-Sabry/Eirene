package be.kdg.eirene.presenter.viewmodel.constraints;

import be.kdg.eirene.presenter.viewmodel.validators.password.PasswordMatchValidator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint (validatedBy = {PasswordMatchValidator.class})
@Target ({ElementType.TYPE})
@Retention (RetentionPolicy.RUNTIME)
public @interface PasswordMatchConstraint {
	String message() default "Passwords do not match";

	Class[] groups() default {};

	Class[] payload() default {};
}

