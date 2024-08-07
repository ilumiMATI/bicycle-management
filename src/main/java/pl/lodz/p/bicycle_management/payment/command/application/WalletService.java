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
        log.info(prefix() + "Creating wallet for user with id " + walletCreateCommand.userId().toString());
        return userWalletRepository.save(
                UserWalletFactory.createUserWallet(
                        UserId.of(walletCreateCommand.userId())
                )
        );
    }

    public void pay(final WalletPayCommand walletPayCommand) {
        log.info(prefix() + "Trying to pay " + walletPayCommand.amount().toString() +
                "with wallet with userId: " + walletPayCommand.userId().toString());

        UserWallet userWallet = userWalletRepository.findByUserId(UserId.of(walletPayCommand.userId()))
                .orElseThrow(UserWalletNotFoundException::new);
        log.info(prefix() + "Wallet found has " + userWallet.getMoney().asString());

        userWallet.pay(Money.of(walletPayCommand.amount()));
        log.info(prefix() + "Wallet payment successful, user has " + userWallet.getMoney().asString() + " now");
    }

    public void payForRent(final RentPaymentCommand rentPaymentCommand) {
        log.info(prefix() + "Trying to pay for rent with wallet with userId: " + rentPaymentCommand.userId().toString());

        UserWallet userWallet = UserWalletFactory.prepareForRentPayment(
                userWalletRepository.findByUserId(UserId.of(rentPaymentCommand.userId()))
                        .orElseThrow(UserWalletNotFoundException::new));
        log.info(prefix() + "Wallet found has " + userWallet.getMoney().asString() + " now");

        userWallet.payForRent(rentPaymentCommand.timeInMinutes());
    }

    public void deposit(final WalletDepositCommand walletDepositCommand) {
        log.info(prefix() + "Trying to deposit " + walletDepositCommand.amount().toString() +
                " to wallet with userId: " + walletDepositCommand.userId().toString());

        UserWallet userWallet = userWalletRepository.findByUserId(UserId.of(walletDepositCommand.userId()))
                .orElseThrow(UserWalletNotFoundException::new);
        log.info(prefix() + "Wallet found has " + userWallet.getMoney().asString());

        userWallet.deposit(Money.of(walletDepositCommand.amount()));
        log.info(prefix() + "Wallet deposit successful, user has " + userWallet.getMoney().asString() + " now");
    }

    public boolean hasMoney(final WalletCheckMoneyCommand walletCheckMoneyCommand) {
        log.info(prefix() + "Trying to check money " + walletCheckMoneyCommand.amount().toString());
        UserWallet userWallet = userWalletRepository.findByUserId(UserId.of(walletCheckMoneyCommand.userId()))
                .orElseThrow(UserWalletNotFoundException::new);
        log.info(prefix() + "Wallet found has " + userWallet.getMoney().asString());
        return userWallet.hasMoney(Money.of(walletCheckMoneyCommand.amount()));
    }

    public void remove(final WalletRemoveCommand walletRemoveCommand) {
        log.info(prefix() + "Removing wallet with user id " + walletRemoveCommand.userId().toString());
        userWalletRepository.removeByUserId(UserId.of(walletRemoveCommand.userId()));
    }

    private String prefix() {
        return "[WalletService] ";
    }
}
