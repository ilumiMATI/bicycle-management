package pl.lodz.p.bicycle_management.rental.query.facade;

import pl.lodz.p.bicycle_management.rental.command.domain.UserId;
import pl.lodz.p.bicycle_management.rental.command.domain.UserRentals;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaQueryUserRentalsRepository extends JpaRepository<UserRentals, Integer> {
    Optional<UserRentals> findByUserId(UserId userId);
}
