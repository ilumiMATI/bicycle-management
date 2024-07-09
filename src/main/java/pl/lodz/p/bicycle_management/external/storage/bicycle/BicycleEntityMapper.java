package pl.lodz.p.bicycle_management.external.storage.bicycle;

import org.mapstruct.Mapper;
import pl.lodz.p.bicycle_management.domain.bicycle.Bicycle;

@Mapper(componentModel = "spring")
public interface BicycleEntityMapper {
    BicycleEntity toEntity(Bicycle bicycle);
    Bicycle toDomain(BicycleEntity bicycleEntity);
}
