package pl.lodz.p.bicycle_management.rental.command.infrastructure.storage;


import org.springframework.stereotype.Repository;
import pl.lodz.p.bicycle_management.rental.command.domain.UserId;
import pl.lodz.p.bicycle_management.rental.command.domain.UserRentals;
import pl.lodz.p.bicycle_management.rental.command.domain.UserRentalsAlreadyExistsException;
import pl.lodz.p.bicycle_management.rental.command.domain.UserRentalsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;


@RequiredArgsConstructor
@Log
@Repository
public class UserRentalsStorageAdapter implements UserRentalsRepository {

    private final JpaUserRentalsRepository jpaUserRentalsRepository;

    @Override
    public UserRentals save(final UserRentals userRentals) {
        try {
            UserRentals saved = jpaUserRentalsRepository.save(userRentals);
            log.info("Saved entity " + saved);
            return saved;
        } catch (DataIntegrityViolationException ex) {
            log.warning("User Rentals for User " + userRentals.getUserId() + " already exits in db");
            throw new UserRentalsAlreadyExistsException();
        }
    }

    @Override
    public void remove(UserId userId) {
        jpaUserRentalsRepository.deleteByUserId(userId);
    }

    @Override
    public Optional<UserRentals> findByUserId(final UserId userId) {
        return jpaUserRentalsRepository.findByUserId(userId);
    }
}
