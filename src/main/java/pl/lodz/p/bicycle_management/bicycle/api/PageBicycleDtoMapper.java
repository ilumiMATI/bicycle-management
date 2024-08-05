package pl.lodz.p.bicycle_management.bicycle.api;

import org.mapstruct.*;
import pl.lodz.p.bicycle_management.bicycle.domain.Bicycle;
import pl.lodz.p.bicycle_management.bicycle.domain.PageBicycle;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PageBicycleDtoMapper {

    @Mapping(target = "bicycles", qualifiedByName = "toBicycleDtoList")
    PageBicycleDto toPageDto(PageBicycle pageBicycle);

    @Named("toBicycleDtoList")
    @IterableMapping(qualifiedByName = "bicycleToBicycleDto")
    List<BicycleDto> toListDto(List<Bicycle> bicycleList);

    @Named("bicycleToBicycleDto")
    @Mapping(target = "bicycleNumber", source = "bicycleNumber.bicycleNumber")
    BicycleDto toDto(Bicycle bicycle);

    @Mapping(target = "bicycles", qualifiedByName = "toBicycleList")
    PageBicycle toPageDomain(PageBicycleDto pageBicycleDto);

    @Named("toBicycleList")
    @IterableMapping(qualifiedByName = "bicycleDtoToBicycle")
    List<Bicycle> toListDomain(List<BicycleDto> bicycleDtoList);

    @Named("bicycleDtoToBicycle")
    @Mapping(target = "bicycleNumber.bicycleNumber", source = "bicycleNumber")
    Bicycle toDomain(BicycleDto bicycleDto);

}
