package pl.lodz.p.bicycle_management.availability.query.facade;

import pl.lodz.p.bicycle_management.availability.command.domain.BicycleAvailability;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PageBicycleAvailabilityDtoMapper  extends BicycleMappingHelper {

    @Mapping(target = "availabilities", qualifiedByName = "toBicycleAvailabilityDtoList")
    PageBicycleAvailabilityDto toPageDto(PageBicycleAvailability domain);

    @Named("toBicycleAvailabilityDtoList")
    @IterableMapping(qualifiedByName = "bicycleAvailabilityToBicycleAvailabilityDto")
    List<BicycleAvailabilityDto> toListDto(List<BicycleAvailability> availabilities);

    @Named("bicycleAvailabilityToBicycleAvailabilityDto")
    @Mapping(source = "bicycleNumber.bicycleNumber", target = "bicycleNumber")
    @Mapping(source = "userId.userId", target = "userId")
    BicycleAvailabilityDto toDto(BicycleAvailability domain);
}