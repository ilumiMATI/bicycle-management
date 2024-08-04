package pl.lodz.p.bicycle_management.auth.api;

public record AuthUserDto(
        Integer id,
        String email,
        String name,
        String password,
        String role
) {}
