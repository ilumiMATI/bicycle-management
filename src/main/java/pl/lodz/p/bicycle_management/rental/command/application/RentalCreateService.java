package pl.lodz.p.bicycle_management.rental.command.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lodz.p.bicycle_management.rental.command.domain.*;

@Service
@RequiredArgsConstructor
@Log
public class RentalCreateService {
    private final UserRentalsRepository userRentalsRepository;

    public UserRentals create(final CreateCommand createCommand) {
        log.info(prefix() + "Creating rentals for " + createCommand.userId());
        return userRentalsRepository.save(UserRentalsFactory.createUserRentals(UserId.of(createCommand.userId())));
    }

    public void remove(final RemoveCommand removeCommand) {
        log.info(prefix() + "Removing rentals for " + removeCommand.userId());
        userRentalsRepository.removeByUserId(UserId.of(removeCommand.userId()));
    }

    private String prefix() {
        return "[RentalCreateService] ";
    }
}