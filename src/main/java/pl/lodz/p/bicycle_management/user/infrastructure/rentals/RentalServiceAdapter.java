package pl.lodz.p.bicycle_management.user.infrastructure.rentals;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.lodz.p.bicycle_management.user.domain.RentalService;

@Component
@RequiredArgsConstructor
public class RentalServiceAdapter implements RentalService {
    private final pl.lodz.p.bicycle_management.rental.command.application.RentalService rentalService;

    @Override
    public void createRentalsForUser(Integer id) {
        rentalService.create(new pl.lodz.p.bicycle_management.rental.command.application.CreateCommand(id));
    }

    @Override
    public void removeRentalsForUser(Integer id) {
        rentalService.remove(new pl.lodz.p.bicycle_management.rental.command.application.RemoveCommand(id));
    }
}
