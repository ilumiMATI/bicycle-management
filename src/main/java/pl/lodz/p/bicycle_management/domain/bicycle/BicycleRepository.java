package pl.lodz.p.bicycle_management.domain.bicycle;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BicycleRepository {
    Bicycle save(Bicycle bicycle);
    Optional<Bicycle> findById(Integer id);
    List<Bicycle> findAll();
    Bicycle update(Bicycle bicycle);
    void delete(Integer id);
}