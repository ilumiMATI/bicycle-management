package pl.lodz.p.bicycle_management.bicycle.domain;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BicycleRepository {
    Bicycle save(Bicycle bicycle);
    Optional<Bicycle> findById(Integer id);
    Optional<Bicycle> findByBicycleId(BicycleId bicycleId);
    List<Bicycle> findAll();
    Bicycle update(Bicycle bicycle);
    void delete(Integer id);
}
