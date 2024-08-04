package pl.lodz.p.bicycle_management.user.api;

public record UserDto(
        Integer id,
        String email,
        String name,
        String password,
        String role
) {}
