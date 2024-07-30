package pl.lodz.p.bicycle_management.user.infrastructure.web;

public record UserDto(
        Integer id,
        String nickname,
        String email
) {
}
