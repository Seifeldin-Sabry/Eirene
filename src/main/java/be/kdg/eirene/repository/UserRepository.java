package be.kdg.eirene.repository;

import be.kdg.eirene.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
//	User createUser(User user);

//	List<User> readUsers();

//	boolean deleteUser(User user);
}
