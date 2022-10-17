package be.kdg.eirene.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class User {
	private final SessionHistory sessionHistory;
	private final long id;
	private final Sex sex;
	private String name;
	private String email;
	private String password;
	private Session session;
	
	public User(String name, String email, String password, Sex sex) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.sessionHistory = new SessionHistory();
		this.session = null;
		this.sex = sex;
		id = new Random().nextLong(); // will change in the future
	}
}
