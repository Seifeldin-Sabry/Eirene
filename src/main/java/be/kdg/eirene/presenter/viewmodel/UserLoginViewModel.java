package be.kdg.eirene.presenter.viewmodel;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserLoginViewModel {

	@NotBlank (message = "Email is required")
	@Email (message = "Email must be valid")
	private String email;

	@NotBlank (message = "Password is required")
	private String password;
}
