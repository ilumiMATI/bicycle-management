package pl.lodz.p.bicycle_management.rental.command.application;

import pl.lodz.p.bicycle_management.rental.command.domain.RentalNumber;
import pl.lodz.p.bicycle_management.rental.command.domain.UserId;

public interface PaymentService {
    void payForRent(RentalNumber rentalNumber, UserId userId, Integer timeInMinutes);
}
