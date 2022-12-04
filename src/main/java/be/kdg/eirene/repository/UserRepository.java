package be.kdg.eirene.repository;

import be.kdg.eirene.model.Gender;
import be.kdg.eirene.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
	boolean existsByEmailIgnoreCase(@NonNull String email);

	User findByEmailIgnoreCase(@NonNull String email);

	@Query (value = "SELECT SUM(EXTRACT(EPOCH FROM (s.end_time - s.start_time))) AS diff FROM sessions s WHERE s.user_id = ?1 AND s.end_time IS NOT NULL GROUP BY s.user_id", nativeQuery = true)
	Optional<Long> getTotalDurationSessionsByUserID(Long userId);

	@Query (value = "SELECT AVG(EXTRACT(EPOCH FROM (s.end_time - s.start_time))) AS diff FROM sessions s WHERE s.user_id = ?1 AND s.end_time IS NOT NULL GROUP BY s.user_id", nativeQuery = true)
	Optional<Long> getAverageDurationSessionsByUserID(Long userId);

	@Transactional
	@Modifying
	@Query (value = "UPDATE users SET name = ?2, email = ?3, gender = CAST(?4 AS gender) WHERE user_id = ?1", nativeQuery = true)
	void updateProfile(Long userId, String name, String email, String gender);

	@Transactional
	@Modifying
	@Query (value = "UPDATE users SET password = ?2 WHERE user_id = ?1", nativeQuery = true)
	void updatePassword(Long userId, String password);

	//	User createUser(User user);

	//	List<User> readUsers();

	//	boolean deleteUser(User user);


}
