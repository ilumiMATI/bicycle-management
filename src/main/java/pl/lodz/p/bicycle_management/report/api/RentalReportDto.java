package pl.lodz.p.bicycle_management.report.api;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record RentalReportDto(
        Integer id,
        String rentalNumber,
        Integer userId,
        String bicycleNumber,
        LocalDateTime rentTime,
        LocalDateTime returnTime
) {
}
