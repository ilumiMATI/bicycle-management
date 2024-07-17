package pl.lodz.p.bicycle_management.bicycle.external.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import pl.lodz.p.bicycle_management.bicycle.domain.Bicycle;
import pl.lodz.p.bicycle_management.bicycle.domain.BicycleNotFoundException;
import pl.lodz.p.bicycle_management.bicycle.domain.BicycleRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BicycleDatabaseStorageAdapter implements BicycleRepository {
    final private JpaBicycleRepository jpaBicycleRepository;

    @Override
    public Bicycle save(Bicycle bicycle) {
        return jpaBicycleRepository.save(bicycle);
    }

    @Override
    public Optional<Bicycle> findById(Integer id) {
        return jpaBicycleRepository.findById(id);
    }

    @Override
    public List<Bicycle> findAll() {
        return jpaBicycleRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));
    }

    @Override
    public Bicycle update(Bicycle bicycle) {
        Optional<Bicycle> bicycleEntity = jpaBicycleRepository.findById(bicycle.getId());
        if (bicycleEntity.isPresent()) {
            return jpaBicycleRepository.save(bicycle);
        }
        throw new BicycleNotFoundException();
    }

    @Override
    public void delete(Integer id) {
        jpaBicycleRepository.deleteById(id);
    }
}
