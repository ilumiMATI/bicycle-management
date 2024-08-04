package pl.lodz.p.bicycle_management.rental.command.domain;

public interface RentingPolicy {
    void rentBicycle(UserRentals userRentals, String bicycleId);
}
