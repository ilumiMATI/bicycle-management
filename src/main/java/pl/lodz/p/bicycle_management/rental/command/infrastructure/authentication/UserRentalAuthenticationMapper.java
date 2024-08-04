package pl.lodz.p.bicycle_management.rental.command.infrastructure.authentication;

import pl.lodz.p.bicycle_management.rental.command.domain.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserRentalAuthenticationMapper {

    User toReservationContext(pl.lodz.p.bicycle_management.user.domain.User user);

}
