package pl.lodz.p.bicycle_management.payment.command.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import pl.lodz.p.bicycle_management.payment.command.domain.*;

@Service
@RequiredArgsConstructor
@Log
public class WalletService {

    private final UserWalletRepository userWalletRepository;

    public UserWallet create(final WalletCreateCommand walletCreateCommand) {
        return userWalletRepository.save(
                UserWalletFactory.createUserWallet(
                        UserId.of(walletCreateCommand.userId())
                )
        );
    }

    public void pay(final WalletPayCommand walletPayCommand) {
        log.info("Trying to pay " + walletPayCommand.amount().toString() +
                "with wallet with userId: " + walletPayCommand.userId().toString());

        UserWallet userWallet = userWalletRepository.findByUserId(UserId.of(walletPayCommand.userId()))
                .orElseThrow(UserWalletNotFoundException::new);
        log.info("Wallet found has " + userWallet.getMoney().asString());

        userWallet.pay(Money.of(walletPayCommand.amount()));
        log.info("Wallet payment successful, user has " + userWallet.getMoney().asString() + " now");
    }

    public void payForRent(final RentPaymentCommand rentPaymentCommand) {
        log.info("Trying to pay for rent with wallet with userId: " + rentPaymentCommand.userId().toString());

        UserWallet userWallet = UserWalletFactory.prepareForRentPayment(
                userWalletRepository.findByUserId(UserId.of(rentPaymentCommand.userId()))
                        .orElseThrow(UserWalletNotFoundException::new));
        log.info("Wallet found has " + userWallet.getMoney().asString() + " now");

        userWallet.payForRent(rentPaymentCommand.timeInMinutes());
    }

    public void deposit(final WalletDepositCommand walletDepositCommand) {
        log.info("Trying to deposit " + walletDepositCommand.amount().toString() +
                " to wallet with userId: " + walletDepositCommand.userId().toString());

        UserWallet userWallet = userWalletRepository.findByUserId(UserId.of(walletDepositCommand.userId()))
                .orElseThrow(UserWalletNotFoundException::new);
        log.info("Wallet found has " + userWallet.getMoney().asString());

        userWallet.deposit(Money.of(walletDepositCommand.amount()));
        log.info("Wallet deposit successful, user has " + userWallet.getMoney().asString() + " now");
    }

    public boolean hasMoney(final WalletCheckMoneyCommand walletCheckMoneyCommand) {
        log.info("Trying to check money " + walletCheckMoneyCommand.amount().toString());
        UserWallet userWallet = userWalletRepository.findByUserId(UserId.of(walletCheckMoneyCommand.userId()))
                .orElseThrow(UserWalletNotFoundException::new);
        log.info("Wallet found has " + userWallet.getMoney().asString());
        return userWallet.hasMoney(Money.of(walletCheckMoneyCommand.amount()));
    }

    public void remove(final WalletRemoveCommand walletRemoveCommand) {
        userWalletRepository.removeByUserId(UserId.of(walletRemoveCommand.userId()));
    }
}
