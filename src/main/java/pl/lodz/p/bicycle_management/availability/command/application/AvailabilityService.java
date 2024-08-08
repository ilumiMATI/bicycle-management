package pl.lodz.p.bicycle_management.availability.command.application;


import lombok.extern.java.Log;
import pl.lodz.p.bicycle_management.availability.command.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
//@Transactional
@RequiredArgsConstructor
@Log
public class AvailabilityService {

    private final BicycleAvailabilityRepository bicycleAvailabilityRepository;

    public BicycleAvailability create(final CreateCommand createCommand) {
        log.info(prefix() + "Creating new bicycle availability for bicycleNumber: " + createCommand.bicycleNumber());
        return bicycleAvailabilityRepository.save(new BicycleAvailability(BicycleNumber.of(createCommand.bicycleNumber())));
    }

    public void remove(final RemoveCommand removeCommand) {
        log.info(prefix() + "Removing bicycle availability for bicycleNumber: " + removeCommand.bicycleNumber());
        bicycleAvailabilityRepository.remove(BicycleNumber.of(removeCommand.bicycleNumber()));
    }

    public BicycleAvailability findByBicycleNumber(BicycleNumber bicycleNumber) {
        log.info(prefix() + "Finding bicycle availability for bicycleNumber: " + bicycleNumber.asString());
        return bicycleAvailabilityRepository.findBy(bicycleNumber)
                .orElseThrow(BicycleNotFoundException::new);
    }

    public void lockBicycle(LockCommand lockCommand) {
        log.info(prefix() + "Locking bicycle with bicycleNumber: " + lockCommand.bicycleNumber());
        BicycleAvailability bicycleAvailability = findByBicycleNumber(BicycleNumber.of(lockCommand.bicycleNumber()));
        bicycleAvailability.lockFor(UserId.of(lockCommand.userId()));
    }

    public LockDuration unlockBicycle(UnlockCommand unlockCommand) {
        log.info(prefix() + "Unlocking bicycle with bicycleNumber: " + unlockCommand.bicycleNumber());
        BicycleAvailability bicycleAvailability = findByBicycleNumber(BicycleNumber.of(unlockCommand.bicycleNumber()));
        return bicycleAvailability.unlock();
    }

    private String prefix() {
        return "[AvailabilityService] ";
    }

}