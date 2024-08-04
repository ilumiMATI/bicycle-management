package pl.lodz.p.bicycle_management.rental.command.infrastructure.storage;


import pl.lodz.p.bicycle_management.rental.command.domain.UserRentals;
import pl.lodz.p.bicycle_management.rental.command.domain.UserRentalsAlreadyExistsException;
import pl.lodz.p.bicycle_management.rental.command.domain.UserRentalsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

@RequiredArgsConstructor
@Log
public
class UserRentalsStorageAdapter implements UserRentalsRepository {

    private final JpaUserRentalsRepository repository;

    @Override
    public UserRentals save(final UserRentals userRentals) {
        try {
            UserRentals saved = repository.save(userRentals);
            log.info("Saved entity " + saved);
            return saved;
        } catch (DataIntegrityViolationException ex) {
            log.warning("User Rentals for User " + userRentals.getUserId() + " already exits in db");
            throw new UserRentalsAlreadyExistsException();
        }
    }

    @Override
    public Optional<UserRentals> findBy(final Integer userId) {
        return repository.findByUserId(userId);
    }
}
