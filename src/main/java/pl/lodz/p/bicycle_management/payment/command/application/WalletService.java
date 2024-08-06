package pl.lodz.p.bicycle_management.payment.command.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lodz.p.bicycle_management.payment.command.domain.*;

@Service
@RequiredArgsConstructor
@Log
public class WalletService { // TODO: This will replace PaymentService?
    private final UserWalletRepository userWalletRepository;

    public UserWallet create(final WalletCreateCommand walletCreateCommand) {
        return userWalletRepository.save(
                UserWalletFactory.createUserWallet(
                        UserId.of(walletCreateCommand.userId())
                )
        );
    }

    public void pay(final WalletPayCommand walletPayCommand) {
        log.info("Trying to pay with wallet with userId: " + walletPayCommand.userId().toString());
        UserWallet userWallet = userWalletRepository.findByUserId(UserId.of(walletPayCommand.userId()))
                .orElseThrow(UserWalletNotFoundException::new);
        log.info("Wallet found has " + userWallet.getMoney().asString());
        userWallet.pay(Money.of(walletPayCommand.amount()));
        log.info("Wallet payment successful, user has " + userWallet.getMoney().asString() + " now");
    }

    public void remove(final WalletRemoveCommand walletRemoveCommand) {
        userWalletRepository.removeByUserId(UserId.of(walletRemoveCommand.userId()));
    }
}
