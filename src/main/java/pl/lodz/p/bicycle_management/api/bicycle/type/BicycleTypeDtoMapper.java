package pl.lodz.p.bicycle_management.api.bicycle.type;

import org.mapstruct.Mapper;
import pl.lodz.p.bicycle_management.domain.bicycle.type.BicycleType;

@Mapper(componentModel = "spring")
public interface BicycleTypeDtoMapper {
    BicycleTypeDto toDto(BicycleType bicycleType);
    BicycleType toDomain(BicycleTypeDto bicycleTypeDto);
}
