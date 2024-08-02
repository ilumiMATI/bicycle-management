package pl.lodz.p.bicycle_management.availability.command.application;

import org.springframework.security.core.Authentication;
import pl.lodz.p.bicycle_management.availability.command.domain.User;

public interface AuthenticationService {
    Authentication getAuthentication();
    User getLoggedInUser();
}
