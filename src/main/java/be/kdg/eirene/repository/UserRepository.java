package be.kdg.eirene.repository;

import be.kdg.eirene.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
	boolean existsByEmailIgnoreCase(@NonNull String email);

	User findByEmailIgnoreCase(@NonNull String email);

	@Query (value = "SELECT SUM(EXTRACT(EPOCH FROM (s.end_time - s.start_time))) AS diff FROM SESSIONS s WHERE s.user_id = ?1 AND s.end_time IS NOT NULL GROUP BY s.user_id", nativeQuery = true)
	Optional<Long> getTotalDurationSessionsByUserID(Long userId);

	@Query (value = "SELECT AVG(EXTRACT(EPOCH FROM (s.end_time - s.start_time))) AS diff FROM SESSIONS s WHERE s.user_id = ?1 AND s.end_time IS NOT NULL GROUP BY s.user_id", nativeQuery = true)
	Optional<Long> getAverageDurationSessionsByUserID(Long userId);

	//	User createUser(User user);

	//	List<User> readUsers();

	//	boolean deleteUser(User user);


}
