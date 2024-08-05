package pl.lodz.p.bicycle_management.user.infrastructure.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.lodz.p.bicycle_management.user.domain.PaymentService;

@Component
@RequiredArgsConstructor
public class UserPaymentServiceAdapter implements PaymentService {
    private final pl.lodz.p.bicycle_management.payment.command.application.UserWalletService userWalletService;
    @Override
    public void createWalletForUser(Integer id) {
        userWalletService.create(
                new pl.lodz.p.bicycle_management.payment.command.application.CreateCommand(id)
        );
    }

    @Override
    public void removeWalletForUser(Integer id) {
        userWalletService.remove(
                new pl.lodz.p.bicycle_management.payment.command.application.RemoveCommand(id)
        );
    }
}
