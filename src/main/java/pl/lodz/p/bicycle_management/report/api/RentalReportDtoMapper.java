package pl.lodz.p.bicycle_management.report.api;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.lodz.p.bicycle_management.report.domain.RentalReport;

@Mapper(componentModel = "spring")
public interface RentalReportDtoMapper {

    @Mapping(target = "userId", source = "userId.userId")
    @Mapping(target = "bicycleNumber", source = "bicycleNumber.bicycleNumber")
    @Mapping(target = "rentalNumber", source = "rentalNumber.rentalNumber")
    RentalReportDto toDto(RentalReport domain);

    @Mapping(target = "userId.userId", source = "userId")
    @Mapping(target = "bicycleNumber.bicycleNumber", source = "bicycleNumber")
    @Mapping(target = "rentalNumber.rentalNumber", source = "rentalNumber")
    RentalReport toDomain(RentalReportDto dto);
}
