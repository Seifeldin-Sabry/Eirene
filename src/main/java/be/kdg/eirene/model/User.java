package be.kdg.eirene.model;

import be.kdg.eirene.util.BcryptPasswordUtil;
import be.kdg.eirene.util.PostgreSQLEnumType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false)
	private Long user_id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "email", nullable = false, length = 50)
	private String email;


	@Column(name = "password", nullable = false, length = 60)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "sex", nullable = false)
	@Type(type = "pgsql_enum")
	private Sex sex;

	@Transient
	private Session session;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@ToString.Exclude
	private List<Session> sessionHistory;
	
	public User(String name, String email, String password, Sex sex) {
		this.name = name;
		this.email = email;
		this.password = BcryptPasswordUtil.hashPassword(password);
		this.sessionHistory = new ArrayList<>();
		this.session = null;
		this.sex = sex;
	}
}
