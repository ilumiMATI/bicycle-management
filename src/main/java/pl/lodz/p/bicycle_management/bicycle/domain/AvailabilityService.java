package pl.lodz.p.bicycle_management.bicycle.domain;

public interface AvailabilityService {
    void createAvailability(BicycleNumber bicycleNumber);
    void removeAvailability(BicycleNumber bicycleNumber);
}
