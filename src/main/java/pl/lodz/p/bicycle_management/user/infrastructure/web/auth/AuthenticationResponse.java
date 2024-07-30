package pl.lodz.p.bicycle_management.user.infrastructure.web.auth;


import pl.lodz.p.bicycle_management.user.infrastructure.web.user.UserDto;

public record AuthenticationResponse(
        String token,
        UserDto userDto
) {
}
