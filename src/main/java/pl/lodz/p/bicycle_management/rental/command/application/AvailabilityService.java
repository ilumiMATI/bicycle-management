package pl.lodz.p.bicycle_management.rental.command.application;

import pl.lodz.p.bicycle_management.rental.command.domain.RentDuration;

public interface AvailabilityService {
    void lockBicycle(String bicycleNumber, Integer userId);
    RentDuration unlockBicycle(String bicycleNumber, Integer userId);
}
