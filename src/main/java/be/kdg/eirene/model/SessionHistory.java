package be.kdg.eirene.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class SessionHistory {
	private List<Session> sessions;

	public SessionHistory() {
		this.sessions = new ArrayList<>();
	}
}
