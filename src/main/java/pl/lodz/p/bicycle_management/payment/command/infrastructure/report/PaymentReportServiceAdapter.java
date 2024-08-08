package pl.lodz.p.bicycle_management.payment.command.infrastructure.report;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.lodz.p.bicycle_management.payment.command.application.ReportService;
import pl.lodz.p.bicycle_management.payment.command.domain.Money;
import pl.lodz.p.bicycle_management.payment.command.domain.RentalNumber;
import pl.lodz.p.bicycle_management.payment.command.domain.UserId;

@Service
@RequiredArgsConstructor
public class PaymentReportServiceAdapter implements ReportService {

    private final pl.lodz.p.bicycle_management.report.domain.RentalPaymentReportService reportService;
    private final PaymentReportServiceMapper mapper;


    @Override
    public void save(RentalNumber rentalNumber, UserId userId, Money paidPrice) {
        reportService.save(
                new pl.lodz.p.bicycle_management.report.domain.RentalPaymentReport(
                        mapper.toReportContext(rentalNumber),
                        mapper.toReportContext(userId),
                        mapper.toReportContext(paidPrice)
                )
        );
    }
}
