package pl.lodz.p.bicycle_management.availability.query.facade;

import pl.lodz.p.bicycle_management.availability.command.domain.BicycleAvailability;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BicycleAvailabilityDtoMapper extends BicycleMappingHelper {

    @Mapping(source = "bicycleNumber.bicycleNumber", target = "bicycleNumber")
    @Mapping(source = "userId.userId", target = "userId")
    BicycleAvailabilityDto toDto(BicycleAvailability domain);

}