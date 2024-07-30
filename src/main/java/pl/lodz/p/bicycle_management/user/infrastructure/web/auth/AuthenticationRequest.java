package pl.lodz.p.bicycle_management.user.infrastructure.web.auth;

public record AuthenticationRequest(
        String username,
        String password
) {
}
