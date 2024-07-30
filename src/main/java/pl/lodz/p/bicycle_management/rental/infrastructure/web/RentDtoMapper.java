package pl.lodz.p.bicycle_management.rental.infrastructure.web;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.lodz.p.bicycle_management.bicycle.domain.Bicycle;
import pl.lodz.p.bicycle_management.rental.domain.Rent;
import pl.lodz.p.bicycle_management.rental.domain.User;

@Mapper(componentModel = "spring")
public interface RentDtoMapper {
    @Mapping(source = "rent.bicycle.id", target = "bicycleId")
    //@Mapping(source = "userId", target = "userId")
    RentMinimalDto toMinimalDto(Rent rent);

    //@Mapping(source = "userId", target = "userId")
    @Mapping(source = "bicycleId", target = "bicycle", qualifiedByName = "bicycleFromId")
    Rent toDomain(RentMinimalDto rentMinimalDto);

    @Named("bicycleFromId")
    static Bicycle mapBicycleFromId(Integer bicycleId) {
        if (bicycleId == null) return null;

        Bicycle bicycle = new Bicycle();
        bicycle.setId(bicycleId);
        return bicycle;
    }

    Rent toDomain(RentDto rentDto);
    RentDto toDto(Rent rent);
}
