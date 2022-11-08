package ricotunes.services.card.service;

import org.springframework.stereotype.Service;
import ricotunes.services.card.model.User;
import ricotunes.services.card.payload.UserSummary;
import ricotunes.services.card.security.UserPrincipal;

@Service
public interface UserService {

	UserSummary getCurrentUser(UserPrincipal currentUser);
	User addUser(User user);

}