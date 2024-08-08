package pl.lodz.p.bicycle_management.report.api;

import java.util.List;

public record PageRentalPaymentReportDto(
        List<RentalPaymentReportDto> reports,
        Integer currentPage,
        Integer totalPages,
        Long totalElements
) {
}
