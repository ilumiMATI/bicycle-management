package pl.lodz.p.bicycle_management.rental.command.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lodz.p.bicycle_management.rental.command.domain.*;

@Service
@RequiredArgsConstructor
public class RentalCreateService {
    private final UserRentalsRepository userRentalsRepository;

    public UserRentals create(final CreateCommand createCommand) {
        return userRentalsRepository.save(UserRentalsFactory.createUserRentals(UserId.of(createCommand.userId())));
    }

    public void remove(final RemoveCommand removeCommand) {
        userRentalsRepository.removeByUserId(UserId.of(removeCommand.userId()));
    }

}