package pl.lodz.p.bicycle_management.report.api;

import org.mapstruct.Mapper;
import pl.lodz.p.bicycle_management.report.domain.RentalReport;

@Mapper(componentModel = "spring")
public interface RentalReportDtoMapper extends ReportsMappingHelper {

    RentalReportDto toDto(RentalReport domain);

    RentalReport toDomain(RentalReportDto dto);

}
