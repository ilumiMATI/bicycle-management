package pl.lodz.p.bicycle_management.report.api;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.lodz.p.bicycle_management.report.domain.PageRentalPaymentReport;
import pl.lodz.p.bicycle_management.report.domain.RentalPaymentReport;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PageRentalPaymentReportDtoMapper extends ReportsMappingHelper {

    @Mapping(target = "reports", qualifiedByName = "toListDto")
    PageRentalPaymentReportDto toPageDto(PageRentalPaymentReport domain);

    @Named("toListDto")
    @IterableMapping(qualifiedByName = "toDto")
    List<RentalPaymentReportDto> toListDto(List<RentalPaymentReport> domainList);

    @Named("toDto")
    RentalPaymentReportDto toDto(RentalPaymentReport domain);

    @Mapping(target = "reports", qualifiedByName = "toListDomain")
    PageRentalPaymentReport toPageDomain(PageRentalPaymentReportDto dto);

    @Named("toListDomain")
    @IterableMapping(qualifiedByName = "toDomain")
    List<RentalPaymentReport> toListDomain(List<RentalPaymentReportDto> dtoList);

    @Named("toDomain")
    RentalPaymentReport toDomain(RentalPaymentReportDto dto);
}
