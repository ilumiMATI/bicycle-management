package pl.lodz.p.bicycle_management.payment.command.domain;

public class UserWalletFactory {
    // TODO: There could be different starting money depending on the User role as
    //       Additional parameter?
    // TODO: Change later on to zero if it's always default
    public static UserWallet createUserWallet(UserId userId) {
        return new UserWallet(userId, Money.of(14.567 * userId.value()));
    }
}
