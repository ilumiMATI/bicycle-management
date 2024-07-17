package pl.lodz.p.bicycle_management.bicycle.external.storage.type;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import pl.lodz.p.bicycle_management.bicycle.domain.type.BicycleType;
import pl.lodz.p.bicycle_management.bicycle.domain.type.BicycleTypeNotFoundException;
import pl.lodz.p.bicycle_management.bicycle.domain.type.BicycleTypeRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BicycleTypeDatabaseStorageAdapter implements BicycleTypeRepository {
    final private JpaBicycleTypeRepository jpaBicycleTypeRepository;

    @Override
    public BicycleType save(BicycleType bicycleType) {
        return jpaBicycleTypeRepository.save(bicycleType);
    }

    @Override
    public Optional<BicycleType> findById(Integer id) {
        return jpaBicycleTypeRepository.findById(id);
    }

    @Override
    public List<BicycleType> findAll() {
        return jpaBicycleTypeRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public BicycleType update(BicycleType bicycleType) {
        Optional<BicycleType> bicycleTypeEntity = jpaBicycleTypeRepository.findById(bicycleType.getId());
        if (bicycleTypeEntity.isPresent()) {
            return jpaBicycleTypeRepository.save(bicycleType);
        }
        throw new BicycleTypeNotFoundException();
    }

    @Override
    public void delete(Integer id) {
        jpaBicycleTypeRepository.deleteById(id);
    }
}