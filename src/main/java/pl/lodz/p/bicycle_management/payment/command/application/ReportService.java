package pl.lodz.p.bicycle_management.payment.command.application;

import pl.lodz.p.bicycle_management.payment.command.domain.Money;
import pl.lodz.p.bicycle_management.payment.command.domain.RentalNumber;
import pl.lodz.p.bicycle_management.payment.command.domain.UserId;

public interface ReportService {
    void save(RentalNumber rentalNumber, UserId userId, Money paidPrice);
}
