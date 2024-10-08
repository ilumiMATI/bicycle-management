package pl.lodz.p.bicycle_management.rental.command.domain;

import java.util.Optional;

public interface UserRentalsRepository {

    UserRentals save(UserRentals userRentals);

    void removeByUserId(UserId userId);

    Optional<UserRentals> findByUserId(UserId userId);

}