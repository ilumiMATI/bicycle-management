package pl.lodz.p.bicycle_management.rental.query.facade;

import pl.lodz.p.bicycle_management.rental.command.domain.UserRentals;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRentalsDtoMapper {

    UserRentalsDto toDto(UserRentals domain);

}