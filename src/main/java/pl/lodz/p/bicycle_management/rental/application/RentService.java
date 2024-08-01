package pl.lodz.p.bicycle_management.rental.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.lodz.p.bicycle_management.rental.domain.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RentService {
    private final RentRepository rentRepository;
    private final AuthenticationService authenticationService;

    @Transactional
    public void createRents(UserId userId, List<BicycleId> bicycles) {
        User loggedUser = authenticationService.getLoggedInUser();
        RentingPolicy rentingPolicy = RentingPolicyFactory.prepareRentingPolicy(loggedUser, userId);
        List<Rent> rents = rentingPolicy.createRents(bicycles);
        List<Rent> savedRents = new ArrayList<>();
        for (Rent rent : rents) {
            savedRents.add(rentRepository.save(rent));
        }
    }

    public Rent save(Rent rent) {
        return rentRepository.save(rent);
    }

    public void delete(RentId rentId) {
        rentRepository.delete(rentId);
    }

    public Rent findByRentNumber(RentNumber rentNumber) {
        return rentRepository.findByRentNumber(rentNumber)
                .orElseThrow(RentNotFoundException::new);
    }

    public PageRent findAll(Pageable pageable) {
        return rentRepository.findAll(pageable);
    }
}
