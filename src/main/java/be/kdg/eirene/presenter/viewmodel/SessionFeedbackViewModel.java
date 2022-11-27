package be.kdg.eirene.presenter.viewmodel;

import be.kdg.eirene.model.SatisfactionLevel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SessionFeedbackViewModel {

	@NotNull (message = "Please select a satisfaction level")
	private SatisfactionLevel satisfactionLevel;

	private String sessionName;
}
