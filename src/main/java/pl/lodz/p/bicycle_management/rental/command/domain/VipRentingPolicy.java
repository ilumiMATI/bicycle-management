package pl.lodz.p.bicycle_management.rental.command.domain;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class VipRentingPolicy implements RentingPolicy {
    @Override
    public void rentBicycle(final UserRentals userRentals, final String bicycleId) {
        final List<String> bicycles = userRentals.getBicycles();
        if (bicycles.size() >= 5) {
            throw new MethodNotAllowedException();
        }
        bicycles.add(bicycleId);
    }
}
