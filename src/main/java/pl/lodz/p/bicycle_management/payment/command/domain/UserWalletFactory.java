package pl.lodz.p.bicycle_management.payment.command.domain;

public class UserWalletFactory {
    public static UserWallet createUserWallet(UserId userId) {
        return new UserWallet(userId, Money.of(0.00));
    }

    // TODO: Later on there should be packets of user which would land here so there will be different RentPaymentPolicies
    public static UserWallet prepareForRentPayment(UserWallet userWallet /*, packet */) {
        userWallet.rentPaymentPolicy = new RegularRentPaymentPolicy();
        return userWallet;
    }
}
