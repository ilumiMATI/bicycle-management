package pl.lodz.p.bicycle_management.payment.command.domain;

public class UserWalletFactory {
    public static UserWallet createUserWallet(UserId userId) {
        return new UserWallet(userId, Money.of(0.00));
    }
}
