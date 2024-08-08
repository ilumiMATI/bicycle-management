package pl.lodz.p.bicycle_management.report.api;

import java.math.BigDecimal;

public record RentalPaymentReportDto(
        Integer id,
        String rentalNumber,
        Integer userId,
        BigDecimal paidPrice
) {
}
