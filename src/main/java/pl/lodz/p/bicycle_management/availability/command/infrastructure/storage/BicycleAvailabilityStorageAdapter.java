package pl.lodz.p.bicycle_management.availability.command.infrastructure.storage;


import pl.lodz.p.bicycle_management.availability.command.domain.BicycleAlreadyExistsException;
import pl.lodz.p.bicycle_management.availability.command.domain.BicycleAvailability;
import pl.lodz.p.bicycle_management.availability.command.domain.BicycleAvailabilityRepository;
import pl.lodz.p.bicycle_management.availability.command.domain.BicycleNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

@RequiredArgsConstructor
@Log
public class BicycleAvailabilityStorageAdapter implements BicycleAvailabilityRepository {

    private final JpaBicycleAvailabilityRepository bicycleAvailabilityRepository;

    @Override
    public BicycleAvailability save(final BicycleAvailability bicycleAvailability) {
        try {
            BicycleAvailability saved = bicycleAvailabilityRepository.save(bicycleAvailability);
            log.info("Saved entity " + saved);
            return saved;
        } catch (DataIntegrityViolationException ex) {
            log.warning("Bicycle Availability with number " + bicycleAvailability.getBicycleNumber().asString() + " already exits in db");
            throw new BicycleAlreadyExistsException();
        }
    }

    @Override
    public void remove(BicycleNumber bicycleNumber) {
        bicycleAvailabilityRepository.deleteByBicycleNumber(bicycleNumber);
    }

    @Override
    public Optional<BicycleAvailability> findBy(final BicycleNumber bicycleNumber) {
        return bicycleAvailabilityRepository.findByBicycleNumber(bicycleNumber);
    }

}
