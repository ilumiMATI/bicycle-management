package pl.lodz.p.bicycle_management.payment.command.application;

import org.springframework.security.core.Authentication;
import pl.lodz.p.bicycle_management.payment.command.domain.User;

public interface AuthenticationService {
    Authentication getAuthentication();
    User getLoggedInUser();
}
