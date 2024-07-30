package pl.lodz.p.bicycle_management.rental.infrastructure.authentication;

import pl.lodz.p.bicycle_management.rental.domain.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserAuthenticationMapper {

    User toRentalContext(pl.lodz.p.bicycle_management.user.domain.User user);

}
