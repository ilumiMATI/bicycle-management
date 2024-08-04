package pl.lodz.p.bicycle_management.availability.command.domain;

import java.util.Optional;

public interface BicycleAvailabilityRepository {

    BicycleAvailability save(BicycleAvailability bicycleAvailability);

    void remove(BicycleNumber bicycleNumber);

    Optional<BicycleAvailability> findBy(BicycleNumber bicycleNumber);

}
