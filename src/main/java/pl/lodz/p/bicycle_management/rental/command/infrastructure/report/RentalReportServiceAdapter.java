package pl.lodz.p.bicycle_management.rental.command.infrastructure.report;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.lodz.p.bicycle_management.rental.command.domain.RentalNumber;
import pl.lodz.p.bicycle_management.rental.command.application.ReportService;
import pl.lodz.p.bicycle_management.rental.command.domain.BicycleNumber;
import pl.lodz.p.bicycle_management.rental.command.domain.UserId;
import pl.lodz.p.bicycle_management.report.domain.RentalReport;
import pl.lodz.p.bicycle_management.report.domain.RentalReportService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RentalReportServiceAdapter implements ReportService {

    private final RentalReportService rentalReportService;
    private final RentalReportServiceMapper mapper;

    @Override
    public RentalNumber save(UserId userId, BicycleNumber bicycleNumber, LocalDateTime startTime, LocalDateTime endTime) {
        RentalReport rentalReport = new RentalReport(mapper.toReportContext(userId), mapper.toReportContext(bicycleNumber), startTime, endTime);
        rentalReportService.save(rentalReport);
        return mapper.toRentalContext(rentalReport.getRentalNumber());
    }
}
