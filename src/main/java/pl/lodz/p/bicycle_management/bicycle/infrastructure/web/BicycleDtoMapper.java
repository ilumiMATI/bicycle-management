package pl.lodz.p.bicycle_management.bicycle.infrastructure.web;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.lodz.p.bicycle_management.bicycle.domain.Bicycle;

@Mapper(componentModel = "spring")
public interface BicycleDtoMapper {

    @Mapping(target = "bicycleId", source = "bicycleId.bicycleId")
    BicycleDto toDto(Bicycle bicycle);

    @Mapping(target = "bicycleId.bicycleId", source = "bicycleId")
    Bicycle toDomain(BicycleDto bicycleDto);
}
