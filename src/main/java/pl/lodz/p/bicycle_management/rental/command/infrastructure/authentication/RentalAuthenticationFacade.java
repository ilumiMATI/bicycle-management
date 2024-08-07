package pl.lodz.p.bicycle_management.rental.command.infrastructure.authentication;


import org.springframework.stereotype.Component;
import pl.lodz.p.bicycle_management.rental.command.application.AuthenticationService;
import pl.lodz.p.bicycle_management.security.UserDetailsImpl;
import pl.lodz.p.bicycle_management.user.domain.User;
import pl.lodz.p.bicycle_management.user.domain.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Component
@RequiredArgsConstructor
public class RentalAuthenticationFacade implements AuthenticationService {
    private final UserService userService;
    private final UserRentalAuthenticationMapper mapper;

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public pl.lodz.p.bicycle_management.rental.command.domain.User getLoggedInUser() {
        Authentication authentication = getAuthentication();
        User user = userService.findByEmail(((UserDetailsImpl) authentication.getPrincipal()).getUsername());
        return mapper.toReservationContext(user);
    }

}