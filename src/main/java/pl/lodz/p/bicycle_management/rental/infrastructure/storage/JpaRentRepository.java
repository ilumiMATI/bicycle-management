package pl.lodz.p.bicycle_management.rental.infrastructure.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.lodz.p.bicycle_management.rental.domain.Rent;

public interface JpaRentRepository extends JpaRepository<Rent, Integer> { }
