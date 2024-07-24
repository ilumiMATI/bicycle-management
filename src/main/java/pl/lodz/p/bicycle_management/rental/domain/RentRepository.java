package pl.lodz.p.bicycle_management.rental.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RentRepository extends CrudRepository<Rent, Integer> {
}
