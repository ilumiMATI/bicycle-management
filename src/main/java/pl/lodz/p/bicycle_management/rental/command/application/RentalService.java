package pl.lodz.p.bicycle_management.rental.command.application;

import pl.lodz.p.bicycle_management.rental.command.domain.*;
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
    private final PaymentService paymentService;

//    public void test() {
//        availabilityService.unlockBicycle(new UnlockCommand("bike1", 1));
//    }

    public UserRentals create(final CreateCommand createCommand) {
        return userRentalsRepository.save(UserRentalsFactory.createUserRentals(UserId.of(createCommand.userId())));
    }

    public void remove(final RemoveCommand removeCommand) {
        userRentalsRepository.removeByUserId(UserId.of(removeCommand.userId()));
    }

    public UserRentals findByUserId(UserId userId) {

        final UserRentals userRentals = userRentalsRepository.findByUserId(userId)
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

        availabilityService.lockBicycle(command.bicycleNumber(), userId);
        UserRentals userRentals = UserRentalsFactory.prepareUserRentalsForUser(findByUserId(UserId.of(userId)), user);
        userRentals.rentBike(command.bicycleNumber());
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

        UserRentals userRentals = UserRentalsFactory.prepareUserRentalsForUser(findByUserId(UserId.of(userId)), user);
        userRentals.returnBike(command.bicycleNumber());

        Integer rentTimeInMinutes = availabilityService.unlockBicycle(command.bicycleNumber(), userId);
        paymentService.payForRent(userId, rentTimeInMinutes);
    }

}