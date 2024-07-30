package pl.lodz.p.bicycle_management.rental.domain;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RentRepository {

    Rent save(Rent rent);
    
    void delete(Integer id);

    Optional<Rent> findById(Integer id);

    Optional<Rent> findByRentNumber(String rentNumber);

    List<Rent> findAll();
}
