package pl.lodz.p.bicycle_management.bicycle.application.type;

import org.mapstruct.Mapper;
import pl.lodz.p.bicycle_management.bicycle.domain.type.BicycleType;

@Mapper(componentModel = "spring")
public interface BicycleTypeDtoMapper {
    BicycleTypeDto toDto(BicycleType bicycleType);
    BicycleType toDomain(BicycleTypeDto bicycleTypeDto);
}
