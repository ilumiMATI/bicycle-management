package pl.lodz.p.bicycle_management.bicycle.application;

import pl.lodz.p.bicycle_management.bicycle.domain.Bicycle;
import pl.lodz.p.bicycle_management.bicycle.domain.BicycleId;

public interface AvailabilityService {
    void createAvailability(BicycleId bicycleId);
    void removeAvailability(BicycleId bicycleId);
}
