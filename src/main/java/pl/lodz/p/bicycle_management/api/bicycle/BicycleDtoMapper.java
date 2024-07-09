package pl.lodz.p.bicycle_management.api.bicycle;

import org.mapstruct.Mapper;
import pl.lodz.p.bicycle_management.domain.bicycle.Bicycle;

@Mapper(componentModel = "spring")
public interface BicycleDtoMapper {
    BicycleDto toDto(Bicycle bicycle);
    Bicycle toDomain(BicycleDto bicycleDto);

}
