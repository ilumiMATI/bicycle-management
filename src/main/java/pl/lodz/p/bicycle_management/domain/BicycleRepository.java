package pl.lodz.p.bicycle_management.domain;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BicycleRepository {
    Bicycle save(Bicycle bicycle);
    Optional<Bicycle> findById(Integer id);
    List<Bicycle> findAll();
    Bicycle update(Integer id, Bicycle bicycle);
    void delete(Integer id);
}
