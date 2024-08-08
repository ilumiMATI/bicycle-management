package pl.lodz.p.bicycle_management.payment.command.infrastructure.report;

import org.mapstruct.Mapper;
import pl.lodz.p.bicycle_management.payment.command.domain.Money;
import pl.lodz.p.bicycle_management.payment.command.domain.RentalNumber;
import pl.lodz.p.bicycle_management.payment.command.domain.UserId;

@Mapper(componentModel = "spring")
public interface PaymentReportServiceMapper {
    pl.lodz.p.bicycle_management.report.domain.RentalNumber toReportContext(RentalNumber rentalNumber);
    pl.lodz.p.bicycle_management.report.domain.UserId toReportContext(UserId id);
    pl.lodz.p.bicycle_management.report.domain.Money toReportContext(Money money);
}
