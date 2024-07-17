package pl.lodz.p.bicycle_management.bicycle.domain.type;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BicycleTypeRepository {
    BicycleType save(BicycleType bicycle);
    Optional<BicycleType> findById(Integer id);
    List<BicycleType> findAll();
    BicycleType update(BicycleType bicycle);
    void delete(Integer id);
}
