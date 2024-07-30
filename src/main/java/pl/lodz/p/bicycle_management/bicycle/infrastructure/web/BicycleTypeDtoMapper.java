package pl.lodz.p.bicycle_management.bicycle.infrastructure.web;

import org.mapstruct.Mapper;
import pl.lodz.p.bicycle_management.bicycle.domain.BicycleType;

@Mapper(componentModel = "spring")
public interface BicycleTypeDtoMapper {
    BicycleTypeDto toDto(BicycleType bicycleType);
    BicycleType toDomain(BicycleTypeDto bicycleTypeDto);
}
