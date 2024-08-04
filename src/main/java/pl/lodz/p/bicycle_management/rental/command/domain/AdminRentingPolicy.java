package pl.lodz.p.bicycle_management.rental.command.domain;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class AdminRentingPolicy implements RentingPolicy{
    @Override
    public void rentBicycle(final UserRentals userRentals, final String bicycleNumber) {
        final List<String> bicycles = userRentals.getBicycles();
        bicycles.add(bicycleNumber);
    }
}
