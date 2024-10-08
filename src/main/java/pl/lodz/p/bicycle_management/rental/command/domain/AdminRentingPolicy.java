package pl.lodz.p.bicycle_management.rental.command.domain;

import lombok.AllArgsConstructor;
import pl.lodz.p.bicycle_management.rental.command.application.WalletService;

import java.util.List;

@AllArgsConstructor
public class AdminRentingPolicy implements RentingPolicy{
    @Override
    public void rentBicycle(final UserRentals userRentals, final BicycleNumber bicycleNumber) {
        final List<BicycleNumber> bicycles = userRentals.getBicycles();
        bicycles.add(bicycleNumber);
    }
}
