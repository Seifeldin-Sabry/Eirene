package be.kdg.eirene.presenter.viewmodel.constraints;

import be.kdg.eirene.presenter.viewmodel.validators.SessionNameValidator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint (validatedBy = {SessionNameValidator.class})
@Target ({ElementType.FIELD})
@Retention (RetentionPolicy.RUNTIME)
public @interface SessionNameConstraint {

	String message() default "Passwords do not match";

	Class[] groups() default {};

	Class[] payload() default {};
}
