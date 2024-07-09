package pl.lodz.p.bicycle_management.domain.bicycle;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BicycleService {
    final private BicycleRepository bicycleRepository;

    public Bicycle addBicycle(Bicycle bicycle) {
        return bicycleRepository.save(bicycle);
    }

    public Optional<Bicycle> findBicycleById(int id) {
        return bicycleRepository.findById(id);
    }

    public List<Bicycle> findAllBicycles() {
        return bicycleRepository.findAll();
    }

    public Bicycle updateBicycle(Bicycle bicycle) {
        return bicycleRepository.update(bicycle);
    }

    public void deleteBicycle(Integer id) {
        bicycleRepository.delete(id);
    }
}
