package be.kdg.eirene.presenter.viewmodel;

import be.kdg.eirene.presenter.viewmodel.constraints.SessionNameConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionFeedback {

	@SessionNameConstraint
	private String sessionName;
}
