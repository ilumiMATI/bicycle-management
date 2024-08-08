package pl.lodz.p.bicycle_management.report.api;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.lodz.p.bicycle_management.report.domain.PageRentalReport;
import pl.lodz.p.bicycle_management.report.domain.RentalReport;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PageRentalReportDtoMapper {

    @Mapping(target = "reports", qualifiedByName = "toListDto")
    PageRentalReportDto toPageDto(PageRentalReport domain);

    @Named("toListDto")
    @IterableMapping(qualifiedByName = "toDto")
    List<RentalReportDto> toListDto(List<RentalReport> domain);

    @Named("toDto")
    @Mapping(target = "userId", source = "userId.userId")
    @Mapping(target = "bicycleNumber", source = "bicycleNumber.bicycleNumber")
    @Mapping(target = "rentalNumber", source = "rentalNumber.rentalNumber")
    RentalReportDto toDto(RentalReport domain);

    @Mapping(target = "reports", qualifiedByName = "toListDomain")
    PageRentalReport toPageDomain(PageRentalReportDto dto);

    @Named("toListDomain")
    @IterableMapping(qualifiedByName = "toDomain")
    List<RentalReport> toListDomain(List<RentalReportDto> dto);

    @Named("toDomain")
    @Mapping(target = "userId.userId", source = "userId")
    @Mapping(target = "bicycleNumber.bicycleNumber", source = "bicycleNumber")
    @Mapping(target = "rentalNumber.rentalNumber", source = "rentalNumber")
    RentalReport toDomain(RentalReportDto dto);
}
