package pl.lodz.p.bicycle_management.rental.infrastructure.web.rent;

import org.mapstruct.Mapper;
import pl.lodz.p.bicycle_management.rental.domain.Rent;

@Mapper(componentModel = "spring")
public interface RentDtoMapper {
    Rent toDomain(RentDto rentDto);
    RentDto toDto(Rent rent);
}
