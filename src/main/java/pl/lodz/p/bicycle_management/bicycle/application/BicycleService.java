package pl.lodz.p.bicycle_management.bicycle.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.lodz.p.bicycle_management.bicycle.domain.Bicycle;
import pl.lodz.p.bicycle_management.bicycle.domain.BicycleNumber;
import pl.lodz.p.bicycle_management.bicycle.domain.BicycleNotFoundException;
import pl.lodz.p.bicycle_management.bicycle.domain.BicycleRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BicycleService {
    private final BicycleRepository bicycleRepository;
    private final AvailabilityService availabilityService;

    public Bicycle addBicycle(Bicycle bicycle) {
        availabilityService.createAvailability(bicycle.getBicycleNumber());
        return bicycleRepository.save(bicycle);
    }

    public Bicycle findBicycleById(Integer id) {
        return bicycleRepository.findById(id)
                .orElseThrow(BicycleNotFoundException::new);
    }

    public Bicycle findBicycleByBicycleNumber(BicycleNumber bicycleNumber) {
        return bicycleRepository.findByBicycleNumber(bicycleNumber)
                .orElseThrow(BicycleNotFoundException::new);
    }

    public List<Bicycle> findAllBicycles() {
        return bicycleRepository.findAll();
    }

    public Bicycle updateBicycle(Bicycle bicycle) {
        return bicycleRepository.update(bicycle);
    }

    public void deleteBicycle(Integer id) {
        availabilityService.removeAvailability(bicycleRepository.findById(id).get().getBicycleNumber());
        bicycleRepository.delete(id);
    }
}
