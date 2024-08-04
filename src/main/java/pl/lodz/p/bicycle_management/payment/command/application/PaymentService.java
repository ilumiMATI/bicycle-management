package pl.lodz.p.bicycle_management.payment.command.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lodz.p.bicycle_management.payment.command.domain.Money;
import pl.lodz.p.bicycle_management.payment.command.domain.UserId;

@Service
@Transactional
@RequiredArgsConstructor
@Log
public class PaymentService {

    public void payForRent(UserId userId, Integer timeInMinutes) {
        log.info("Paying rent for " + userId + " with time " + timeInMinutes);
        // calculation of money
        Money moneyToPay = Money.of(5.5 * timeInMinutes);
        log.info("User " + userId + " must pay " + moneyToPay.toString());
        // payment
        payWithWallet(userId,moneyToPay);
    }

    public void payWithWallet(UserId userId, Money money) {
        log.info("Paying with wallet");
    }
}
