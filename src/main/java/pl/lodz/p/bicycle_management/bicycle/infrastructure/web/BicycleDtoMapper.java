package pl.lodz.p.bicycle_management.bicycle.infrastructure.web;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.lodz.p.bicycle_management.bicycle.domain.Bicycle;
import pl.lodz.p.bicycle_management.bicycle.domain.BicycleType;

@Mapper(componentModel = "spring")
public interface BicycleDtoMapper {
    @Mapping(source = "bicycle.bicycleType.id", target = "bicycleTypeId")
    BicycleMinimalDto toMinimalDto(Bicycle bicycle);

    @Mapping(source = "bicycleTypeId", target = "bicycleType", qualifiedByName = "bicycleTypeFromId")
    Bicycle toDomain(BicycleMinimalDto bicycleMinimalDto);

    @Named("bicycleTypeFromId")
    static BicycleType mapBicycleTypeFromId(Integer id) {
        if (id == null) {
            return null;
        }
        BicycleType bicycleType = new BicycleType();
        bicycleType.setId(id);
        return bicycleType;
    }

    BicycleDto toDto(Bicycle bicycle);
    Bicycle toDomain(BicycleDto bicycleDto);
}