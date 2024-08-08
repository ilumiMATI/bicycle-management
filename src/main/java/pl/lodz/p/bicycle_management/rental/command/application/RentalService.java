package pl.lodz.p.bicycle_management.rental.command.application;

import lombok.extern.java.Log;
import pl.lodz.p.bicycle_management.rental.command.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
@RequiredArgsConstructor
@Log
public class RentalService {

    private final AuthenticationService authenticationService;
    private final UserRentalsRepository userRentalsRepository;
    private final AvailabilityService availabilityService;
    private final PaymentService paymentService;
    private final WalletService walletService;
    private final ReportService reportService;

    public UserRentals create(final CreateCommand createCommand) {
        log.info(prefix() + "Creating rentals for " + createCommand.userId());
        return userRentalsRepository.save(UserRentalsFactory.createUserRentals(UserId.of(createCommand.userId())));
    }

    public void remove(final RemoveCommand removeCommand) {
        log.info(prefix() + "Removing rentals for " + removeCommand.userId());
        userRentalsRepository.removeByUserId(UserId.of(removeCommand.userId()));
    }

    public UserRentals findByUserId(UserId userId) {
        log.info(prefix() + "finding user with id " + userId.value());
        final UserRentals userRentals = userRentalsRepository.findByUserId(userId)
                .orElseThrow(UserRentalsNotFoundException::new);
        return userRentals;
    }

    public void rentBike(RentCommand command) {
        log.info(prefix() + "Renting bicycle " + command.bicycleNumber() + " for user with id " + command.userId());
        User user = authenticationService.getLoggedInUser();
        Integer userId;

        if (command.userId() == null || command.userId().equals(user.id())) {
            userId = user.id();
        } else {
            if (user.role() != UserRole.ADMIN) {
                throw new MethodNotAllowedException();
            }
            userId = command.userId();
        }

        // TODO: Is this okay place to check for minimal money?
        //       Is it better to put this into renting policy somehow?
        if (user.role() != UserRole.ADMIN && !walletService.hasMoney(UserId.of(userId), BigDecimal.valueOf(10.00))) {
            log.warning(prefix() + "User has no minimal funds to rent bicycle");
            throw new NoMinimalFundsException();
        }

        availabilityService.lockBicycle(command.bicycleNumber(), userId);
        UserRentals userRentals = UserRentalsFactory.prepareUserRentalsForUser(findByUserId(UserId.of(userId)), user);

        userRentals.rentBike(BicycleNumber.of(command.bicycleNumber()));
    }

    public void returnBike(ReturnCommand command) {
        log.info(prefix() + "Returning bicycle " + command.bicycleNumber() + " for user with id " + command.userId());
        User user = authenticationService.getLoggedInUser();
        Integer userId;

        if (command.userId() == null || command.userId().equals(user.id())) {
            userId = user.id();
        } else {
            if (user.role() != UserRole.ADMIN) {
                throw new MethodNotAllowedException();
            }
            userId = command.userId();
        }

        UserRentals userRentals = UserRentalsFactory.prepareUserRentalsForUser(findByUserId(UserId.of(userId)), user);
        userRentals.returnBike(BicycleNumber.of(command.bicycleNumber()));

        // Unlocking bicycle availability and receiving RentDuration
        RentDuration rentDuration = availabilityService.unlockBicycle(command.bicycleNumber(), userId);
        RentalNumber rentalNumber = reportService.save(
                UserId.of(userId),
                BicycleNumber.of(command.bicycleNumber()),
                rentDuration.startTime(),
                rentDuration.endTime()
        );


        paymentService.payForRent(userId, rentDuration.inMinutes());
    }

    private String prefix() {
        return "[RentalService] ";
    }
}