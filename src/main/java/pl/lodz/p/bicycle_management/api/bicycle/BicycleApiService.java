package pl.lodz.p.bicycle_management.api.bicycle;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.bicycle_management.domain.Bicycle;
import pl.lodz.p.bicycle_management.domain.BicycleRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BicycleApiService {
    final private BicycleRepository bicycleRepository;

    public Bicycle addBicycle(Bicycle bicycle) {
        bicycleRepository.save(bicycle);
        return bicycle;
    }

    public Optional<Bicycle> findBicycleById(int id) {
        return bicycleRepository.findById(id);
    }

    public List<Bicycle> findAllBicycles() {
        return bicycleRepository.findAll();
    }

    public Bicycle updateBicycle(Integer id, Bicycle bicycle) {
        return bicycleRepository.update(id, bicycle);
    }

    public void deleteBicycle(Integer id) {
        bicycleRepository.delete(id);
    }
}
