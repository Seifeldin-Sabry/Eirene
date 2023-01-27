package be.kdg.eirene.presenter.viewmodel;

public interface PasswordMatcher {
	default boolean passwordsMatch() {
		return getPassword().equals(getConfirmPassword());
	}

	String getPassword();

	String getConfirmPassword();
}
