package pl.lodz.p.bicycle_management.bicycle.infrastructure.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.bicycle_management.bicycle.domain.BicycleType;

public interface JpaBicycleTypeRepository extends JpaRepository<BicycleType, Integer> {
}
