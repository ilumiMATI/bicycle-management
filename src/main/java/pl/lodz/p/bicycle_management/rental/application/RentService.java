package pl.lodz.p.bicycle_management.rental.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.lodz.p.bicycle_management.rental.domain.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Log
public class RentService {
    private final RentRepository rentRepository;
    private final AuthenticationService authenticationService;

    @Transactional
    public void createRents(UserId userId, List<BicycleId> bicycles) {
        User loggedUser = authenticationService.getLoggedInUser();
        RentingPolicy rentingPolicy = RentServicePolicyFactory.prepareRentingPolicy(loggedUser);
        List<Rent> rents = rentingPolicy.createRents(this,loggedUser.getId(),userId,bicycles);
        List<Rent> savedRents = new ArrayList<>();
        for (Rent rent : rents) {
            savedRents.add(rentRepository.save(rent));
        }
    }

    public List<Rent> getMyRents() {
        User loggedUser = authenticationService.getLoggedInUser();
        return rentRepository.findByUserId(loggedUser.getId());
    }

    @Transactional
    public void returnRent(RentNumber rentNumber) {
        User loggedUser = authenticationService.getLoggedInUser();
        ReturnPolicy returnPolicy = RentServicePolicyFactory.prepareReturnPolicy(loggedUser);
        Money moneyToCharge = returnPolicy.finalizeRent(this,loggedUser.getId(), rentNumber);
        log.info("User needs to be charged " + moneyToCharge.getAmount().toString());
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

    public Rent findById(RentId rentId) {
        return rentRepository.findById(rentId)
                .orElseThrow(RentNotFoundException::new);
    }

    public boolean existsByUserId(UserId userId) {
        return rentRepository.existsByUserId(userId);
    }

    public PageRent findAll(Pageable pageable) {
        return rentRepository.findAll(pageable);
    }
}
