package pl.lodz.p.bicycle_management.report.api;

import java.util.List;

public record PageRentalReportDto(
        List<RentalReportDto> reports,
        Integer currentPage,
        Integer totalPages,
        Long totalElements
) {
}
