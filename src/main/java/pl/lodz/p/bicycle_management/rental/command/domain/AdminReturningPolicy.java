package pl.lodz.p.bicycle_management.rental.command.domain;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class AdminReturningPolicy implements ReturningPolicy{
    @Override
    public void returnBicycle(final UserRentals userRentals, final BicycleNumber bicycleNumber) {
        final List<BicycleNumber> bicycles = userRentals.getBicycles();
        bicycles.remove(bicycleNumber);
    }
}
