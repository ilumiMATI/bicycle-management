package pl.lodz.p.bicycle_management.user.infrastructure.web.user;

public record UserDto(
        Integer id,
        String email,
        String name,
        String password,
        String role
) {}