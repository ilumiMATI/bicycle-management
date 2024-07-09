package pl.lodz.p.bicycle_management.external.storage.bicycle;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBicycleRepository extends JpaRepository<BicycleEntity, Integer> { }