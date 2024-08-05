package pl.lodz.p.bicycle_management.bicycle.infrastructure.availability;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.lodz.p.bicycle_management.bicycle.api.AvailabilityService;
import pl.lodz.p.bicycle_management.bicycle.domain.BicycleNumber;

@RequiredArgsConstructor
@Component
public class AvailabilityAdapter implements AvailabilityService {
    private final pl.lodz.p.bicycle_management.availability.command.application.AvailabilityService availabilityService;

    @Override
    public void createAvailability(BicycleNumber bicycleNumber) {
        availabilityService.create(new pl.lodz.p.bicycle_management.availability.command.application.CreateCommand(bicycleNumber.asString()));
    }

    @Override
    public void removeAvailability(BicycleNumber bicycleNumber) {
        availabilityService.remove(new pl.lodz.p.bicycle_management.availability.command.application.RemoveCommand(bicycleNumber.asString()));
    }
}
