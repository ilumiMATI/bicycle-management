package pl.lodz.p.bicycle_management.rental.command.infrastructure.availability;

import pl.lodz.p.bicycle_management.availability.command.application.LockCommand;
import pl.lodz.p.bicycle_management.availability.command.application.UnlockCommand;
import pl.lodz.p.bicycle_management.rental.command.application.AvailabilityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RentalAvailabilityFacade implements AvailabilityService {
    public final pl.lodz.p.bicycle_management.availability.command.application.AvailabilityService availabilityService;

    @Override
    public void lockBicycle(final String bicycleNumber, Integer userId) {
        availabilityService.lockBicycle(new LockCommand(bicycleNumber, userId));
    }

    @Override
    public Integer unlockBicycle(final String bicycleNumber, Integer userId) {
        return availabilityService.unlockBicycle(new UnlockCommand(bicycleNumber, userId));
    }
}
