package pl.lodz.p.bicycle_management.rental.command.infrastructure.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.lodz.p.bicycle_management.payment.command.application.WalletCheckMoneyCommand;
import pl.lodz.p.bicycle_management.rental.command.application.WalletService;
import pl.lodz.p.bicycle_management.rental.command.domain.UserId;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class RentalWalletServiceAdapter implements WalletService {
    private final pl.lodz.p.bicycle_management.payment.command.application.WalletService walletService;

    @Override
    public boolean hasMoney(UserId userId, BigDecimal amount) {
        return walletService.hasMoney(
                new WalletCheckMoneyCommand(userId.value(), amount)
        );
    }
}
