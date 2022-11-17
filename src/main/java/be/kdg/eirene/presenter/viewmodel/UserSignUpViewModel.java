package be.kdg.eirene.presenter.viewmodel;

import be.kdg.eirene.model.Gender;
import be.kdg.eirene.presenter.viewmodel.constraints.PasswordMatchConstraint;
import be.kdg.eirene.presenter.viewmodel.constraints.UniqueEmailConstraint;
import be.kdg.eirene.presenter.viewmodel.validators.password.LetterCheck;
import be.kdg.eirene.presenter.viewmodel.validators.password.NumberCheck;
import be.kdg.eirene.presenter.viewmodel.validators.password.SizeCheck;
import be.kdg.eirene.presenter.viewmodel.validators.password.SpecialCharacterCheck;
import lombok.Getter;
import lombok.Setter;

import javax.validation.GroupSequence;
import javax.validation.constraints.*;

@Getter
@Setter
@PasswordMatchConstraint
@GroupSequence ({UserSignUpViewModel.class, SizeCheck.class, LetterCheck.class, NumberCheck.class, SpecialCharacterCheck.class})
public class UserSignUpViewModel {

	@NotBlank (message = "Name is required")
	@Size (min = 2, max = 50, message = "Name must be between 2 and 50 characters", groups = SizeCheck.class)
	private String name;

	@NotBlank (message = "Email is required")
	@Email (message = "Email must be valid")
	@UniqueEmailConstraint
	private String email;


	@NotBlank (message = "Password is required")
	@Size (min = 8, message = "Password must be at least 8 characters long", groups = SizeCheck.class)
	@Pattern.List ({
			@Pattern (regexp = ".*[A-Z].*", message = "Password must contain at least one uppercase character", groups = LetterCheck.class),
			@Pattern (regexp = ".*[0-9].*", message = "Password must contain at least one digit", groups = NumberCheck.class),
			@Pattern (regexp = ".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*", message = "Password must contain at least one special character", groups = SpecialCharacterCheck.class),
	})
	private String password;

	@NotBlank (message = "Please confirm your password")
	private String confirmPassword;

	private Gender gender;

	@AssertTrue (message = "You must accept the terms and conditions")
	private boolean termsAccepted;
}
