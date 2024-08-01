package pl.lodz.p.bicycle_management.rental.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RentRepository {

    Rent save(Rent rent);
    
    void delete(RentId rentId);

    Optional<Rent> findByRentNumber(RentNumber rentNumber);

    Optional<Rent> findById(RentId rentId);

    PageRent findAll(final Pageable pageable);
}
