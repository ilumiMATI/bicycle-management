package pl.lodz.p.bicycle_management.user.infrastructure.web;

import org.mapstruct.Mapper;
import pl.lodz.p.bicycle_management.user.domain.User;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    UserDto toDto(User user);
    User toDomain(UserDto userDto);
}
