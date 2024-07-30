package pl.lodz.p.bicycle_management.rental.infrastructure.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.lodz.p.bicycle_management.security.UserDetailsImpl;
import pl.lodz.p.bicycle_management.user.application.UserService;
import pl.lodz.p.bicycle_management.user.domain.User;
import pl.lodz.p.bicycle_management.rental.application.AuthenticationService;

@Component
@RequiredArgsConstructor
public class AuthenticationFacade implements AuthenticationService {
    private final UserService userService;
    private final UserAuthenticationMapper mapper;
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public Integer getLoggedInUserId() {
        Authentication authentication = getAuthentication();
        User user = userService.findByEmail(((UserDetailsImpl) authentication.getPrincipal()).getUsername());
        return user.getId();
    }

    @Override
    public pl.lodz.p.bicycle_management.rental.domain.User getLoggedInUser() {
        Authentication authentication = getAuthentication();
        User user = userService.findByEmail(((UserDetailsImpl) authentication.getPrincipal()).getUsername());
        return mapper.toRentalContext(user);
//        return new com.rafalnowak.cinema.reservation.domain.User(user.getId(), user.getRole());
    }

}