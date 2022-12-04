package be.kdg.eirene.presenter.viewmodel;

import be.kdg.eirene.model.Gender;
import be.kdg.eirene.presenter.viewmodel.validators.password.SizeCheck;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.GroupSequence;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@GroupSequence ({UserEditViewModel.class, SizeCheck.class})
@AllArgsConstructor
public class UserEditViewModel {

	@NotBlank (message = "Name is required")
	@Size (min = 2, max = 50, message = "Name must be between 2 and 50 characters", groups = SizeCheck.class)
	private String name;

	@NotBlank (message = "Email is required")
	@Email (message = "Email must be valid")
	private String email;

	private Gender gender;

}
