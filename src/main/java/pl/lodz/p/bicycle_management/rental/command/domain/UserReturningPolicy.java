package pl.lodz.p.bicycle_management.rental.command.domain;

import java.util.List;

public class UserReturningPolicy implements ReturningPolicy {
    @Override
    public void returnBicycle(final UserRentals userRentals, final String bicycleNumber) {
        final List<String> bicycles = userRentals.getBicycles();
        bicycles.remove(bicycleNumber);
    }
}
