package pl.lodz.p.bicycle_management.availability.command.application;

import pl.lodz.p.bicycle_management.availability.command.domain.User;
import org.springframework.security.core.Authentication;


public interface AuthenticationService {
    Authentication getAuthentication();
    User getLoggedInUser();
}
