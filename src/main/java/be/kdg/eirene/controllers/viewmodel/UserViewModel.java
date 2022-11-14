package be.kdg.eirene.controllers.viewmodel;

import be.kdg.eirene.model.Sex;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserViewModel {
	@NotBlank (message = "name is required")
	@Size (min = 2, max = 40, message = "name must be between 2 and 40 characters")
	private String name;
	@NotBlank (message = "email is required")
	@Email (message = "email must be valid")
	private String email;
	@NotBlank (message = "password is required")
	@Size (min = 8, max = 20, message = "password must be between 8 and 20 characters")
	private String password;
	@NotBlank (message = "passwords don't match")
	private String confirmPassword;
	@NotBlank (message = ("incorrect value"))
	private Sex sex;
}
