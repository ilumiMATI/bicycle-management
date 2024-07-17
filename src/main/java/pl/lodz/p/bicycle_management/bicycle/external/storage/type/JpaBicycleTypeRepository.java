package pl.lodz.p.bicycle_management.bicycle.external.storage.type;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.bicycle_management.bicycle.domain.type.BicycleType;

public interface JpaBicycleTypeRepository extends JpaRepository<BicycleType, Integer> {
}
