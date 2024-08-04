package pl.lodz.p.bicycle_management.rental.command.application;

public interface AvailabilityService {
    void lockBicycle(String bicycleId, Integer userId);
    Integer unlockBicycle(String bicycleId, Integer userId);
}
