package pl.lodz.p.bicycle_management.availability.query.facade;

import pl.lodz.p.bicycle_management.availability.command.domain.BicycleAvailability;
import pl.lodz.p.bicycle_management.availability.command.domain.BicycleId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaQueryBicycleAvailabilityRepository extends JpaRepository<BicycleAvailability, Integer> {
    Optional<BicycleAvailability> findByBicycleId(BicycleId bicycleId);
}
