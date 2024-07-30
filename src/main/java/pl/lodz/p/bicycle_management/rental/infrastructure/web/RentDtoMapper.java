package pl.lodz.p.bicycle_management.rental.infrastructure.web;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.lodz.p.bicycle_management.bicycle.domain.Bicycle;
import pl.lodz.p.bicycle_management.rental.domain.Rent;
import pl.lodz.p.bicycle_management.user.domain.User;

@Mapper(componentModel = "spring")
public interface RentDtoMapper {
    @Mapping(source = "rent.user.id", target = "userId")
    @Mapping(source = "rent.bicycle.id", target = "bicycleId")
    RentMinimalDto toMinimalDto(Rent rent);

    @Mapping(source = "userId", target = "user", qualifiedByName = "userFromId")
    @Mapping(source = "bicycleId", target = "bicycle", qualifiedByName = "bicycleFromId")
    Rent toDomain(RentMinimalDto rentMinimalDto);

    @Named("userFromId")
    static User mapUserFromId(Integer userId) {
        if (userId == null) return null;

        User user = new User();
        user.setId(userId);
        return user;
    }

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
