package pl.lodz.p.bicycle_management.payment.command.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lodz.p.bicycle_management.payment.command.domain.Money;
import pl.lodz.p.bicycle_management.payment.command.domain.PaymentInsufficientFundsException;
import pl.lodz.p.bicycle_management.payment.command.domain.UserId;

@Service
@Transactional
@RequiredArgsConstructor
@Log
public class PaymentService {
    private final WalletService walletService;

    public void payForRent(UserId userId, Integer timeInMinutes) {
        log.info("Paying rent for " + userId + " with time " + timeInMinutes);
        // calculation of money
        Money moneyToPay = Money.of(1.2 * timeInMinutes);
        log.info("User " + userId + " must pay " + moneyToPay.asString());
        // payment
//        payWithWallet(userId,moneyToPay);
        try {
            walletService.pay(new WalletPayCommand(userId.value(), moneyToPay.value()));
        } catch (PaymentInsufficientFundsException ex) {
            log.warning("User " + userId + " cannot pay " + moneyToPay.asString());
        }
    }
//    // TODO: Create wallet aggregate with wallet policies ... (In construction)
//    public void payWithWallet(UserId userId, Money money) {
//        log.info("Paying with wallet");
//    }
}
