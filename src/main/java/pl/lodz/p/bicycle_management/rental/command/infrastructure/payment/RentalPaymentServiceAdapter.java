package pl.lodz.p.bicycle_management.rental.command.infrastructure.payment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.lodz.p.bicycle_management.rental.command.application.PaymentService;
import pl.lodz.p.bicycle_management.rental.command.domain.RentalNumber;
import pl.lodz.p.bicycle_management.rental.command.domain.UserId;


@Service
@AllArgsConstructor
public class RentalPaymentServiceAdapter implements PaymentService {

    private final pl.lodz.p.bicycle_management.payment.command.application.PaymentService paymentService;

    @Override
    public void payForRent(RentalNumber rentalNumber, UserId userId, Integer timeInMinutes) {
        paymentService.payForRent(new pl.lodz.p.bicycle_management.payment.command.application.RentPaymentCommand(
                rentalNumber.asString(), userId.value(), timeInMinutes
        ));
    }
}
