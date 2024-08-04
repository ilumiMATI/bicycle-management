package pl.lodz.p.bicycle_management.auth.api;

public record AuthenticationRequest(
        String username,
        String password
) {
}
