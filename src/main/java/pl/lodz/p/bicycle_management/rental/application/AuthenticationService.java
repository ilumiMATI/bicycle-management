package pl.lodz.p.bicycle_management.rental.application;

import org.springframework.security.core.Authentication;
import pl.lodz.p.bicycle_management.rental.domain.User;

public interface AuthenticationService {
    Authentication getAuthentication();
    Integer getLoggedInUserId();
    User getLoggedInUser();
}
