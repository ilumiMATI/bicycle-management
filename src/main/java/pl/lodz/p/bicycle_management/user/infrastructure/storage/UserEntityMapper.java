package pl.lodz.p.bicycle_management.user.infrastructure.storage;

import org.mapstruct.Mapper;
import pl.lodz.p.bicycle_management.user.domain.User;


@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    UserEntity toEntity(User domain);

    User toDomain(UserEntity entity);

}