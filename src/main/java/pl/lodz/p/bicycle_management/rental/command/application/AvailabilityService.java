package pl.lodz.p.bicycle_management.rental.command.application;

public interface AvailabilityService {
    void lockBicycle(String bicycleId, Integer userId);
    void unlockBicycle(String bicycleId, Integer userId);
}
