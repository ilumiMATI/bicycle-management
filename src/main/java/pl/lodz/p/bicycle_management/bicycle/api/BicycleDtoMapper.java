package pl.lodz.p.bicycle_management.bicycle.api;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.lodz.p.bicycle_management.bicycle.domain.Bicycle;

@Mapper(componentModel = "spring")
public interface BicycleDtoMapper {

    @Mapping(target = "bicycleNumber", source = "bicycleNumber.bicycleNumber")
    BicycleDto toDto(Bicycle bicycle);

    @Mapping(target = "bicycleNumber.bicycleNumber", source = "bicycleNumber")
    Bicycle toDomain(BicycleDto bicycleDto);
}
