package be.kdg.eirene.repository;

import be.kdg.eirene.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	//	User createUser(User user);

	//	List<User> readUsers();

	//	boolean deleteUser(User user);
}
