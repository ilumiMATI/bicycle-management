package pl.lodz.p.bicycle_management.report.api;

import org.mapstruct.Mapper;
import pl.lodz.p.bicycle_management.report.domain.RentalPaymentReport;

@Mapper(componentModel = "spring")
public interface RentalPaymentReportDtoMapper extends ReportsMappingHelper {
    RentalPaymentReportDto toDto(RentalPaymentReport domain);
    RentalPaymentReport toDomain(RentalPaymentReportDto dto);
}
