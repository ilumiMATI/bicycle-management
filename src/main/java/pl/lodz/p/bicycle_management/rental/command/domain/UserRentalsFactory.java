package pl.lodz.p.bicycle_management.rental.command.domain;


import lombok.RequiredArgsConstructor;
import pl.lodz.p.bicycle_management.payment.command.application.WalletService;

@RequiredArgsConstructor
public class UserRentalsFactory {

    public static UserRentals createUserRentals(UserId userId) {
        return new UserRentals(userId);
    }

    public static UserRentals prepareUserRentalsForUser(UserRentals userRentals, User user) {
        if (user.role() == UserRole.ADMIN) {
            userRentals.setRentingPolicy(new AdminRentingPolicy());
            userRentals.setReturningPolicy(new AdminReturningPolicy());
            return userRentals;
        }
        if (user.role() == UserRole.VIP) {
            userRentals.setRentingPolicy(new VipRentingPolicy());
            userRentals.setReturningPolicy(new UserReturningPolicy());
            return userRentals;
        }

        userRentals.setRentingPolicy(new UserRentingPolicy());
        userRentals.setReturningPolicy(new UserReturningPolicy());
        return userRentals;

    }
}
