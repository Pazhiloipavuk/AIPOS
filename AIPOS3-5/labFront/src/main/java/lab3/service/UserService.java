package lab3.service;

import lab3.model.User;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

import java.util.Optional;

public interface UserService extends MainService<User> {
    Optional<User> findByUsername(String username);

    User authorize(OAuth2AuthenticatedPrincipal principal);

    Optional<User> getCurrentUser();
}
