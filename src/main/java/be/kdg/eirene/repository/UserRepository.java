package be.kdg.eirene.repository;

import be.kdg.eirene.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

public interface UserRepository extends CrudRepository<User, Long> {
	boolean existsByEmailIgnoreCase(@NonNull String email);

	User findByEmailIgnoreCase(@NonNull String email);
	//	User createUser(User user);

	//	List<User> readUsers();

	//	boolean deleteUser(User user);


}
