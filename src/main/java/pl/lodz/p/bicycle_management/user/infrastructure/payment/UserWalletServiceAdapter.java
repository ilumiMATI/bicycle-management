package pl.lodz.p.bicycle_management.user.infrastructure.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.lodz.p.bicycle_management.user.domain.UserWalletService;

@Component
@RequiredArgsConstructor
public class UserWalletServiceAdapter implements UserWalletService {
    private final pl.lodz.p.bicycle_management.payment.command.application.WalletService walletService;
    @Override
    public void createWalletForUser(Integer id) {
        walletService.create(
                new pl.lodz.p.bicycle_management.payment.command.application.WalletCreateCommand(id)
        );
    }

    @Override
    public void removeWalletForUser(Integer id) {
        walletService.remove(
                new pl.lodz.p.bicycle_management.payment.command.application.WalletRemoveCommand(id)
        );
    }
}
