package pl.lodz.p.bicycle_management.bicycle.infrastructure.availability;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.lodz.p.bicycle_management.bicycle.application.AvailabilityService;
import pl.lodz.p.bicycle_management.bicycle.domain.BicycleId;

@RequiredArgsConstructor
@Component
public class AvailabilityAdapter implements AvailabilityService {
    private final pl.lodz.p.bicycle_management.availability.command.application.AvailabilityService availabilityService;
    @Override
    public void createAvailability(BicycleId bicycleId) {
        availabilityService.create(new pl.lodz.p.bicycle_management.availability.command.application.CreateCommand(bicycleId.asString()));
    }

    @Override
    public void removeAvailability(BicycleId bicycleId) {
        availabilityService.remove(new pl.lodz.p.bicycle_management.availability.command.application.RemoveCommand(bicycleId.asString()));
    }
}
