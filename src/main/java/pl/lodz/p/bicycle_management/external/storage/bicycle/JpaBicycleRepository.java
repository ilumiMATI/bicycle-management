package pl.lodz.p.bicycle_management.external.storage.bicycle;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.bicycle_management.domain.bicycle.Bicycle;

public interface JpaBicycleRepository extends JpaRepository<Bicycle, Integer> { }