package pl.lodz.p.bicycle_management.rental.infrastructure.storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.lodz.p.bicycle_management.rental.domain.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Log
public class RentStorageAdapter implements RentRepository {
    private final JpaRentRepository jpaRentRepository;

    @Override
    public Rent save(Rent rent) {
        try {
            rent.setTimeRented(LocalDateTime.now());
            Rent savedRent = jpaRentRepository.save(rent);
            log.info("Saved Rent: " + savedRent);
            return savedRent;
        } catch (DataIntegrityViolationException ex) {
            log.warning("Rent with number " + rent.getRentNumber() + " already exists");
            throw new RentAlreadyExistsException();
        }
    }

    @Override
    public void delete(RentId rentId) {
        jpaRentRepository.findById(rentId).ifPresent(rent -> jpaRentRepository.deleteById(rentId));
    }

    @Override
    public Optional<Rent> findByRentNumber(RentNumber rentNumber) {
        return jpaRentRepository.findByRentNumber(rentNumber);
    }

    @Override
    public Optional<Rent> findById(RentId rentId) {
        return jpaRentRepository.findById(rentId);
    }

    @Override
    public PageRent findAll(final Pageable pageable) {
        Page<Rent> pageOfRentsEntity = jpaRentRepository.findAll(pageable);
        List<Rent> rentsOnCurrentPage = new ArrayList<>(pageOfRentsEntity.getContent());
        return new PageRent(
                rentsOnCurrentPage,
                pageable.getPageNumber() + 1,
                pageOfRentsEntity.getTotalPages(),
                pageOfRentsEntity.getTotalElements()
        );
    }
}
