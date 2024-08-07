package pl.lodz.p.bicycle_management.rental.command.domain;

import pl.lodz.p.bicycle_management.rental.command.application.WalletService;

public interface RentingPolicy {
    void rentBicycle(UserRentals userRentals, String bicycleNumber);
}
