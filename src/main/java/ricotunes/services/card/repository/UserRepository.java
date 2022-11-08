package ricotunes.services.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ricotunes.services.card.model.User;


import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//	Optional<User> findByUsername(@NotBlank String username);
//
//	User findByEmail(@NotBlank String email);

	Boolean existsByUsername(@NotBlank String username);

	Boolean existsByEmail(@NotBlank String email);

	Boolean existsByPhone(@NotBlank String phone);

	Optional<User> findByUsernameOrEmail(String username, String email);

	User findByEmail(String usernameOrEmail);

	User findByPhone(String phone);

//    User findByPhone(String phone);
}
