package ricotunes.services.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ricotunes.services.card.enums.RoleName;
import ricotunes.services.card.model.Role;


import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

//	Role findByName(RoleName name);
	Optional<Role> findByRoleName(RoleName name);
}
