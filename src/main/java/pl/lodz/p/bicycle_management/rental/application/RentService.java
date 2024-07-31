package pl.lodz.p.bicycle_management.rental.application;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.lodz.p.bicycle_management.rental.domain.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RentService {
    final private RentRepository rentRepository;

    public Rent save(Rent rent) {
        return rentRepository.save(rent);
    }

    public void delete(Integer id) {
        rentRepository.delete(id);
    }

    public Rent findByRentNumber(String rentNumber) {
        return rentRepository.findByRentNumber(rentNumber)
                .orElseThrow(RentNotFoundException::new);
    }

    public PageRent findAll(Pageable pageable) {
        return rentRepository.findAll(pageable);
    }
}
