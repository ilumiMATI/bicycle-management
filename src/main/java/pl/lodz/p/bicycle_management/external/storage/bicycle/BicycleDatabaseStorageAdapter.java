package pl.lodz.p.bicycle_management.external.storage.bicycle;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import pl.lodz.p.bicycle_management.domain.bicycle.Bicycle;
import pl.lodz.p.bicycle_management.domain.bicycle.BicycleRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BicycleDatabaseStorageAdapter implements BicycleRepository {
    final private JpaBicycleRepository jpaBicycleRepository;
    final private BicycleEntityMapper bicycleEntityMapper;

    @Override
    public Bicycle save(Bicycle bicycle) {
        BicycleEntity bicycleEntity = bicycleEntityMapper.toEntity(bicycle);
        return bicycleEntityMapper.toDomain(jpaBicycleRepository.save(bicycleEntity));
    }

    @Override
    public Optional<Bicycle> findById(Integer id) {
        return jpaBicycleRepository.findById(id).map(bicycleEntityMapper::toDomain);
    }

    @Override
    public List<Bicycle> findAll() {
        return jpaBicycleRepository.findAll(Sort.by(Sort.Direction.ASC,"id")).stream().map(bicycleEntityMapper::toDomain).toList();
    }

    // TODO: There should be a BicycleNotFound Exception right?
    @Override
    public Bicycle update(Integer id, Bicycle bicycle) {
        Optional<BicycleEntity> bicycleEntity = jpaBicycleRepository.findById(id);
        BicycleEntity bicycleEntityToUpdate = null;
        if (bicycleEntity.isPresent()) {
            bicycle.setId(id);
            bicycleEntityToUpdate = jpaBicycleRepository.save(bicycleEntityMapper.toEntity(bicycle));
        }
        return bicycleEntityMapper.toDomain(bicycleEntityToUpdate);
    }

    @Override
    public void delete(Integer id) {
        jpaBicycleRepository.deleteById(id);
    }
}
