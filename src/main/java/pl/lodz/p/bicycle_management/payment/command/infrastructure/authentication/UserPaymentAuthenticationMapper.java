package pl.lodz.p.bicycle_management.payment.command.infrastructure.authentication;

import org.mapstruct.Mapper;
import pl.lodz.p.bicycle_management.payment.command.domain.User;


@Mapper(componentModel = "spring")
public interface UserPaymentAuthenticationMapper {

    User toPaymentContext(pl.lodz.p.bicycle_management.user.domain.User user);

}
