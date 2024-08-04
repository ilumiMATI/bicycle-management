package pl.lodz.p.bicycle_management.rental.command.application;

import pl.lodz.p.bicycle_management.rental.command.domain.User;
import org.springframework.security.core.Authentication;

public interface AuthenticationService {
    Authentication getAuthentication();
    User getLoggedInUser();
}
