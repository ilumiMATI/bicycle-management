package pl.lodz.p.bicycle_management.bicycle.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BicycleRepository {
    Bicycle save(Bicycle bicycle);
    Optional<Bicycle> findById(Integer id);
    Optional<Bicycle> findByBicycleNumber(BicycleNumber bicycleNumber);
    PageBicycle findAll(Pageable pageable);
    Bicycle update(Bicycle bicycle);
    void delete(Integer id);
}
