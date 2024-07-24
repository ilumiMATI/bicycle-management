package pl.lodz.p.bicycle_management.user.application;

public record UserDto(
        Integer id,
        String nickname,
        String email
) {
}
