package pl.lodz.p.bicycle_management.payment.command.infrastructure.user;

import org.mapstruct.Mapper;
import pl.lodz.p.bicycle_management.payment.command.domain.User;

@Mapper(componentModel = "spring")
public interface UserPaymentUserServiceMapper {

    User toPaymentContext(pl.lodz.p.bicycle_management.user.domain.User user);
}
