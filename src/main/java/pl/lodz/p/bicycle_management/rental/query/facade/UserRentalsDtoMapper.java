package pl.lodz.p.bicycle_management.rental.query.facade;

import org.mapstruct.Mapping;
import pl.lodz.p.bicycle_management.rental.command.domain.UserRentals;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRentalsDtoMapper {

    @Mapping(target = "userId", source = "userId.userId")
    UserRentalsDto toDto(UserRentals domain);

}