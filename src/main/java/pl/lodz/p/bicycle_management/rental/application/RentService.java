package pl.lodz.p.bicycle_management.rental.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.lodz.p.bicycle_management.rental.domain.Rent;
import pl.lodz.p.bicycle_management.rental.domain.RentNotFoundException;
import pl.lodz.p.bicycle_management.rental.domain.RentRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RentService {
    final private RentRepository rentRepository;

    public Rent addRent(Rent rent) {
        return rentRepository.save(rent);
    }
    public Optional<Rent> findRentById(Integer id) {
        return rentRepository.findById(id);
    }
    public Iterable<Rent> findAllRents() {
        return rentRepository.findAll();
    }
    public Rent updateRent(Rent rent) {
        Optional<Rent> optionalRent = rentRepository.findById(rent.getId());
        if (optionalRent.isPresent()) {
            return rentRepository.save(rent);
        }
        throw new RentNotFoundException();
    }
    public void deleteRent(Integer id) {
        rentRepository.deleteById(id);
    }
}
