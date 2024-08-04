package pl.lodz.p.bicycle_management.rental.command.application;

public interface AvailabilityService {
    void lockBicycle(String bicycleNumber, Integer userId);
    Integer unlockBicycle(String bicycleNumber, Integer userId);
}
