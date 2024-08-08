package pl.lodz.p.bicycle_management.rental.query.facade;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.lodz.p.bicycle_management.bicycle.domain.Bicycle;
import pl.lodz.p.bicycle_management.rental.command.domain.BicycleNumber;
import pl.lodz.p.bicycle_management.rental.command.domain.UserRentals;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserRentalsDtoMapper extends BicycleNumberMappingHelper{

    @Mapping(target = "userId", source = "userId.userId")
//    @Mapping(target = "bicycles", qualifiedByName = "bicycleNumberToStringList")
    UserRentalsDto toDto(UserRentals domain);

//    @Named("bicycleNumberToStringList")
////    @IterableMapping(qualifiedByName = "bicycleNumberAsString")
//    List<String> bicycleNumberToStringList(List<BicycleNumber> bicycles);

//    @Named("bicycleNumberAsString")
//    @Mapping(target = "", source = "bicycleNumber")
//    String asString(BicycleNumber bicycle);

}