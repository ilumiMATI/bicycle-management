package pl.lodz.p.bicycle_management.availability.command.application;


import pl.lodz.p.bicycle_management.availability.command.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AvailabilityService {

    private final BicycleAvailabilityRepository bicycleAvailabilityRepository;
    private final AuthenticationService authenticationService;

    public BicycleAvailability create(final CreateCommand createCommand) {
        return bicycleAvailabilityRepository.save(new BicycleAvailability(BicycleId.of(createCommand.bicycleId())));
    }

    public BicycleAvailability findByBicycleId(BicycleId bicycleId) {

        final BicycleAvailability bicycleAvailability = bicycleAvailabilityRepository.findBy(bicycleId)
                .orElseThrow(BicycleNotFoundException::new);
        return bicycleAvailability;
    }

    public void lockBicycle(LockCommand lockCommand) {
        System.out.println("####### lock bicycle");
        User user = authenticationService.getLoggedInUser();
        BicycleAvailability bicycleAvailability = findByBicycleId(BicycleId.of(lockCommand.bicycleId()));
        if (lockCommand.userId() == null) {
            bicycleAvailability.lockFor(UserId.of(user.id()));
        } else {
            if (user.role() != UserRole.ADMIN) {
                throw new MethodNotAllowedException();
            }
            bicycleAvailability.lockFor(UserId.of(lockCommand.userId()));
        }


    }

    public void unlockBicycle(UnlockCommand unlockCommand) {
        User user = authenticationService.getLoggedInUser();
        BicycleAvailability bicycleAvailability = findByBicycleId(BicycleId.of(unlockCommand.bicycleId()));
        bicycleAvailability.unlock();
    }

}