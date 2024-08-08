package pl.lodz.p.bicycle_management.user.infrastructure.rentals;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.lodz.p.bicycle_management.user.domain.RentalService;

@Component
@Transactional
@RequiredArgsConstructor
public class UserRentalServiceAdapter implements RentalService {
    private final pl.lodz.p.bicycle_management.rental.command.application.RentalCreateService rentalCreateService;

    @Override
    public void createRentalsForUser(Integer id) {
        rentalCreateService.create(new pl.lodz.p.bicycle_management.rental.command.application.CreateCommand(id));
    }

    @Override
    public void removeRentalsForUser(Integer id) {
        rentalCreateService.remove(new pl.lodz.p.bicycle_management.rental.command.application.RemoveCommand(id));
    }
}
