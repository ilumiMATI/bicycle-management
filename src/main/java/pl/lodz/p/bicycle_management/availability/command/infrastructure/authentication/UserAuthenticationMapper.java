package pl.lodz.p.bicycle_management.availability.command.infrastructure.authentication;


import org.mapstruct.Mapper;
import pl.lodz.p.bicycle_management.availability.command.domain.User;


@Mapper(componentModel = "spring")
public interface UserAuthenticationMapper {

    User toAvailabilityContext(pl.lodz.p.bicycle_management.user.domain.User user);

}
