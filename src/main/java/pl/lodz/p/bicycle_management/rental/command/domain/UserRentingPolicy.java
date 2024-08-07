package pl.lodz.p.bicycle_management.rental.command.domain;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import pl.lodz.p.bicycle_management.rental.command.application.WalletService;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
public class UserRentingPolicy implements RentingPolicy {

    @Override
    public void rentBicycle(final UserRentals userRentals, final String bicycleNumber) {
        final List<String> bicycles = userRentals.getBicycles();
        if (bicycles.size() >= 2) {
            throw new MethodNotAllowedException();
        }

        bicycles.add(bicycleNumber);
    }
}
