package pl.lodz.p.bicycle_management.rental.infrastructure.storage;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.bicycle_management.rental.domain.*;

import java.util.List;
import java.util.Optional;

public interface JpaRentRepository extends JpaRepository<Rent, RentId> {
    Optional<Rent> findByRentNumber(RentNumber rentNumber);
    List<Rent> findByUserId(UserId userId);
    boolean existsByUserId(UserId userId);
}
