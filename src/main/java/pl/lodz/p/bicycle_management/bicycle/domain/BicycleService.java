package pl.lodz.p.bicycle_management.bicycle.domain;

import lombok.extern.java.Log;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Log
public class BicycleService {
    private final BicycleRepository bicycleRepository;
    private final AvailabilityService availabilityService;

    public Bicycle save(Bicycle bicycle) {
        log.info(prefix() + "Saving bicycle: " + bicycle.toString());
        Bicycle savedBicycle = bicycleRepository.save(bicycle);
        availabilityService.createAvailability(savedBicycle.getBicycleNumber());
        return savedBicycle;
    }

    public Bicycle findById(Integer id) {
        log.info(prefix() + "Finding bicycle by id: " + id.toString());
        return bicycleRepository.findById(id)
                .orElseThrow(BicycleNotFoundException::new);
    }

    public Bicycle findByBicycleNumber(BicycleNumber bicycleNumber) {
        log.info(prefix() + "Finding bicycle by bicycleNumber: " + bicycleNumber.asString());
        return bicycleRepository.findByBicycleNumber(bicycleNumber)
                .orElseThrow(BicycleNotFoundException::new);
    }

    public PageBicycle findAll(Pageable pageable) {
        log.info(prefix() + "Finding bicycles");
        return bicycleRepository.findAll(pageable);
    }

    public Bicycle update(Bicycle bicycle) {
        log.info(prefix() + "Updating bicycle with bicycleNumber: " + bicycle.getBicycleNumber().asString());
        return bicycleRepository.update(bicycle);
    }

    public void removeById(Integer id) {
        log.info(prefix() + "Removing bicycle with id: " + id);
        availabilityService.removeAvailability(bicycleRepository.findById(id).get().getBicycleNumber());
        bicycleRepository.delete(id);
    }

    private String prefix() {
        return "[BicycleService] ";
    }
}
