package pl.lodz.p.bicycle_management.external.storage.bicycle.type;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.bicycle_management.domain.bicycle.type.BicycleType;

public interface JpaBicycleTypeRepository extends JpaRepository<BicycleType, Integer> {
}
