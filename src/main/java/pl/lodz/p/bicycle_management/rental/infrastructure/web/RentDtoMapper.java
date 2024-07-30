package pl.lodz.p.bicycle_management.rental.infrastructure.web;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.lodz.p.bicycle_management.bicycle.domain.Bicycle;
import pl.lodz.p.bicycle_management.rental.domain.Rent;
import pl.lodz.p.bicycle_management.rental.domain.User;

@Mapper(componentModel = "spring")
public interface RentDtoMapper {
    Rent toDomain(RentDto rentDto);
    RentDto toDto(Rent rent);
}
