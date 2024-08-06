package pl.lodz.p.bicycle_management.bicycle.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class BicycleService {
    private final BicycleRepository bicycleRepository;
    private final AvailabilityService availabilityService;

    public Bicycle save(Bicycle bicycle) {
        Bicycle savedBicycle = bicycleRepository.save(bicycle);
        availabilityService.createAvailability(savedBicycle.getBicycleNumber());
        return savedBicycle;
    }

    public Bicycle findById(Integer id) {
        return bicycleRepository.findById(id)
                .orElseThrow(BicycleNotFoundException::new);
    }

    public Bicycle findByBicycleNumber(BicycleNumber bicycleNumber) {
        return bicycleRepository.findByBicycleNumber(bicycleNumber)
                .orElseThrow(BicycleNotFoundException::new);
    }

    public PageBicycle findAll(Pageable pageable) {
        return bicycleRepository.findAll(pageable);
    }

    public Bicycle update(Bicycle bicycle) {
        return bicycleRepository.update(bicycle);
    }

    public void removeById(Integer id) {
        availabilityService.removeAvailability(bicycleRepository.findById(id).get().getBicycleNumber());
        bicycleRepository.delete(id);
    }
}
