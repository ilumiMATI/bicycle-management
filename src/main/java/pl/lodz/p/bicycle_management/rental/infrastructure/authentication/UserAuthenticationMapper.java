package pl.lodz.p.bicycle_management.rental.infrastructure.authentication;

import org.mapstruct.Mapping;
import pl.lodz.p.bicycle_management.rental.domain.User;
import org.mapstruct.Mapper;
import pl.lodz.p.bicycle_management.rental.infrastructure.web.rent.RentDtoMapper;
import pl.lodz.p.bicycle_management.rental.infrastructure.web.rent.UserIdMapping;


@Mapper(componentModel = "spring", uses = RentDtoMapper.class)
public interface UserAuthenticationMapper {
    @Mapping(target = "id", qualifiedBy = UserIdMapping.ToUserId.class)
    User toRentalContext(pl.lodz.p.bicycle_management.user.domain.User user);

}
