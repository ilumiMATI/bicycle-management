package pl.lodz.p.bicycle_management.bicycle.infrastructure.storage;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import pl.lodz.p.bicycle_management.bicycle.domain.Bicycle;
import pl.lodz.p.bicycle_management.bicycle.domain.BicycleId;

import java.util.Optional;

public interface JpaBicycleRepository extends JpaRepository<Bicycle, Integer> {
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    Optional<Bicycle> findByBicycleId(BicycleId bicycleId);
}