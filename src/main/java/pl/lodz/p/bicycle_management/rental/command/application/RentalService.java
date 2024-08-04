package pl.lodz.p.bicycle_management.rental.command.application;

import pl.lodz.p.bicycle_management.rental.command.domain.MethodNotAllowedException;
import pl.lodz.p.bicycle_management.rental.command.domain.User;
import pl.lodz.p.bicycle_management.rental.command.domain.UserRentals;
import pl.lodz.p.bicycle_management.rental.command.domain.UserRentalsFactory;
import pl.lodz.p.bicycle_management.rental.command.domain.UserRentalsNotFoundException;
import pl.lodz.p.bicycle_management.rental.command.domain.UserRentalsRepository;
import pl.lodz.p.bicycle_management.rental.command.domain.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RentalService {


    private final AuthenticationService authenticationService;
    private final UserRentalsRepository userRentalsRepository;
    private final AvailabilityService availabilityService;

//    public void test() {
//        availabilityService.unlockBicycle(new UnlockCommand("bike1", 1));
//    }

    public UserRentals create(final CreateCommand createCommand) {
        return userRentalsRepository.save(UserRentalsFactory.createUserRentals(createCommand.userId()));
    }

    public UserRentals findByUserId(Integer userId) {

        final UserRentals userRentals = userRentalsRepository.findBy(userId)
                .orElseThrow(UserRentalsNotFoundException::new);
        return userRentals;
    }

    public void rentBike(RentCommand command) {
        User user = authenticationService.getLoggedInUser();
        Integer userId;

        if (command.userId() == null) {
            userId = user.id();
        } else {
            if (user.role() != UserRole.ADMIN) {
                throw new MethodNotAllowedException();
            }
            userId = command.userId();
        }

        availabilityService.lockBicycle(command.bicycleId(), userId);
        UserRentals userRentals = UserRentalsFactory.prepareUserRentalsForUser(findByUserId(userId), user);
        userRentals.rentBike(command.bicycleId());
    }

    public void returnBike(ReturnCommand command) {
        User user = authenticationService.getLoggedInUser();
        Integer userId;

        if (command.userId() == null) {
            userId = user.id();
        } else {
            if (user.role() != UserRole.ADMIN) {
                throw new MethodNotAllowedException();
            }
            userId = command.userId();
        }

        UserRentals userRentals = UserRentalsFactory.prepareUserRentalsForUser(findByUserId(userId), user);
        userRentals.returnBike(command.bicycleId());
        availabilityService.unlockBicycle(command.bicycleId(), userId);
    }

}