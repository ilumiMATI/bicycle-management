package pl.lodz.p.bicycle_management.user.infrastructure.web.user;

import java.util.List;

public record PageUserDto(
        List<UserDto> users,
        Integer currentPage,
        Integer totalPages,
        Long totalElements
) {
}
