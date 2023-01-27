package be.kdg.eirene.presenter.viewmodel;

import be.kdg.eirene.presenter.viewmodel.constraints.PasswordMatchConstraint;
import be.kdg.eirene.presenter.viewmodel.validators.password.LetterCheck;
import be.kdg.eirene.presenter.viewmodel.validators.password.NumberCheck;
import be.kdg.eirene.presenter.viewmodel.validators.password.SizeCheck;
import be.kdg.eirene.presenter.viewmodel.validators.password.SpecialCharacterCheck;
import lombok.Getter;
import lombok.Setter;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@PasswordMatchConstraint
@GroupSequence ({PasswordEditViewModel.class, SizeCheck.class, LetterCheck.class, NumberCheck.class, SpecialCharacterCheck.class})
public class PasswordEditViewModel implements PasswordMatcher {

	@NotBlank (message = "Old password is required")
	private String oldPassword;

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

}
