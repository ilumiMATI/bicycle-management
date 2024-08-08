package pl.lodz.p.bicycle_management.report.api;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.lodz.p.bicycle_management.report.domain.PageRentalReport;
import pl.lodz.p.bicycle_management.report.domain.RentalReport;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PageRentalReportDtoMapper extends ReportsMappingHelper {

    @Mapping(target = "reports", qualifiedByName = "toListDto")
    PageRentalReportDto toPageDto(PageRentalReport domain);

    @Named("toListDto")
    @IterableMapping(qualifiedByName = "toDto")
    List<RentalReportDto> toListDto(List<RentalReport> domain);

    @Named("toDto")
    RentalReportDto toDto(RentalReport domain);

    @Mapping(target = "reports", qualifiedByName = "toListDomain")
    PageRentalReport toPageDomain(PageRentalReportDto dto);

    @Named("toListDomain")
    @IterableMapping(qualifiedByName = "toDomain")
    List<RentalReport> toListDomain(List<RentalReportDto> dto);

    @Named("toDomain")
    RentalReport toDomain(RentalReportDto dto);
}
