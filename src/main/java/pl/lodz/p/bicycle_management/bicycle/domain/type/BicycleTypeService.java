package pl.lodz.p.bicycle_management.bicycle.domain.type;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BicycleTypeService {
    final private BicycleTypeRepository bicycleTypeRepository;

    public BicycleType addBicycleType(BicycleType bicycleType) {
        return bicycleTypeRepository.save(bicycleType);
    }

    public Optional<BicycleType> findBicycleTypeById(Integer id) {
        return bicycleTypeRepository.findById(id);
    }

    public List<BicycleType> findAllBicycleTypes() {
        return bicycleTypeRepository.findAll();
    }

    public BicycleType updateBicycleType(BicycleType bicycleType) {
        return bicycleTypeRepository.update(bicycleType);
    }

    public void deleteBicycleType(Integer id) {
        bicycleTypeRepository.delete(id);
    }
}
