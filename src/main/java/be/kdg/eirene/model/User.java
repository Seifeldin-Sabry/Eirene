package be.kdg.eirene.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {
	@Setter(AccessLevel.NONE)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "sex", nullable = false)
	private Sex sex;

	@OneToOne(cascade = CascadeType.ALL)
	private Session session;

	@Transient
	private SessionHistory sessionHistory;
	
	public User(String name, String email, String password, Sex sex) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.sessionHistory = new SessionHistory();
		this.session = null;
		this.sex = sex;
	}
}
