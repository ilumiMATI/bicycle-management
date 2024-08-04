package pl.lodz.p.bicycle_management.auth.api;


public record AuthenticationResponse(
        String token,
        AuthUserDto userDto
) {
}
