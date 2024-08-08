package pl.lodz.p.bicycle_management.rental.command.domain;

public interface ReturningPolicy {
    void returnBicycle(UserRentals userRentals, BicycleNumber bicycleNumber);
}
