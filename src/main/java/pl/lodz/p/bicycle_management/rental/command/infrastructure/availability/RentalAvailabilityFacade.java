package pl.lodz.p.bicycle_management.rental.command.infrastructure.availability;

import pl.lodz.p.bicycle_management.availability.command.application.LockCommand;
import pl.lodz.p.bicycle_management.availability.command.application.UnlockCommand;
import pl.lodz.p.bicycle_management.rental.command.application.AvailabilityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.lodz.p.bicycle_management.rental.command.domain.RentDuration;

@Service
@AllArgsConstructor
public class RentalAvailabilityFacade implements AvailabilityService {
    private final pl.lodz.p.bicycle_management.availability.command.application.AvailabilityService availabilityService;
    private final RentalAvailabilityMapper rentalAvailabilityMapper;

    @Override
    public void lockBicycle(final String bicycleNumber, Integer userId) {
        availabilityService.lockBicycle(new LockCommand(bicycleNumber, userId));
    }

    @Override
    public RentDuration unlockBicycle(final String bicycleNumber, Integer userId) {
        return rentalAvailabilityMapper.toRentalContext(
                availabilityService.unlockBicycle(new UnlockCommand(bicycleNumber, userId))
        );
    }
}
