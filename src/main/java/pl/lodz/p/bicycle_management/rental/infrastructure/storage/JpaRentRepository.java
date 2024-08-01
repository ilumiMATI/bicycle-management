package pl.lodz.p.bicycle_management.rental.infrastructure.storage;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.lodz.p.bicycle_management.rental.domain.PageRent;
import pl.lodz.p.bicycle_management.rental.domain.Rent;
import pl.lodz.p.bicycle_management.rental.domain.RentId;
import pl.lodz.p.bicycle_management.rental.domain.RentNumber;

import java.util.Optional;

public interface JpaRentRepository extends JpaRepository<Rent, RentId> {
    Optional<Rent> findByRentNumber(RentNumber rentNumber);
}
