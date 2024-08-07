package pl.lodz.p.bicycle_management.rental.command.infrastructure.payment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.lodz.p.bicycle_management.rental.command.application.PaymentService;


@Service
@AllArgsConstructor
public class RentalPaymentServiceAdapter implements PaymentService {

    private final pl.lodz.p.bicycle_management.payment.command.application.PaymentService paymentService;

    @Override
    public void payForRent(Integer userId, Integer timeInMinutes) {
        paymentService.payForRent(new pl.lodz.p.bicycle_management.payment.command.application.RentPaymentCommand(
                userId, timeInMinutes
        ));
    }
}
