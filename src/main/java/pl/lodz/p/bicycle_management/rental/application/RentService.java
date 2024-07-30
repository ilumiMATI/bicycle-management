package pl.lodz.p.bicycle_management.rental.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.lodz.p.bicycle_management.rental.domain.Rent;
import pl.lodz.p.bicycle_management.rental.domain.RentNotFoundException;
import pl.lodz.p.bicycle_management.rental.domain.RentRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RentService {
    final private RentRepository rentRepository;

    public Rent create(Rent rent) {
        return rentRepository.save(rent);
    }
    public Optional<Rent> findById(Integer id) {
        return rentRepository.findById(id);
    }
    public Optional<Rent> findByRentNumber(String rentNumber) {
        return rentRepository.findByRentNumber(rentNumber);
    }
    public List<Rent> findAll() {
        return rentRepository.findAll();
    }
    public Rent update(Rent rent) {
        Optional<Rent> optionalRent = rentRepository.findById(rent.getId());
        if (optionalRent.isPresent()) {
            return rentRepository.save(rent);
        }
        throw new RentNotFoundException();
    }
    public void delete(Integer id) {
        rentRepository.delete(id);
    }
}
