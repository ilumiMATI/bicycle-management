package pl.lodz.p.bicycle_management.bicycle.external.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.bicycle_management.bicycle.domain.Bicycle;

public interface JpaBicycleRepository extends JpaRepository<Bicycle, Integer> { }