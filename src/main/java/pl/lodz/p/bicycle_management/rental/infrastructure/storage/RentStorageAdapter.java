package pl.lodz.p.bicycle_management.rental.infrastructure.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.lodz.p.bicycle_management.rental.domain.Rent;
import pl.lodz.p.bicycle_management.rental.domain.RentRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class RentStorageAdapter implements RentRepository {
    private final JpaRentRepository jpaRentRepository;

    @Override
    public Rent save(Rent rent) {
        return jpaRentRepository.save(rent);
    }

    @Override
    public void delete(Integer id) {
        jpaRentRepository.deleteById(id);
    }

    @Override
    public Optional<Rent> findById(Integer id) {
        return jpaRentRepository.findById(id);
    }

    @Override
    public List<Rent> findAll() {
        return jpaRentRepository.findAll();
    }
}
