package pl.lodz.p.bicycle_management.bicycle.api;

import pl.lodz.p.bicycle_management.bicycle.domain.BicycleNumber;

public interface AvailabilityService {
    void createAvailability(BicycleNumber bicycleNumber);
    void removeAvailability(BicycleNumber bicycleNumber);
}
