package pl.lodz.p.bicycle_management.payment.command.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.lodz.p.bicycle_management.payment.command.domain.*;

import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
@Log
public class PaymentService { // This is more general service which Later on could also use cards ...

    private final WalletService walletService;
    private final UserService userService;
    private final ReportService reportService;

    public void payForRent(RentPaymentCommand rentPaymentCommand) {
        log.info(prefix() + "Paying rent for " + rentPaymentCommand.userId() + " with time of " + rentPaymentCommand.timeInMinutes() + " minutes");
        if (userService.getUser(UserId.of(rentPaymentCommand.userId())).role() == UserRole.ADMIN) {
            log.info(prefix() + "Admin doesn't have to pay");
            return;
        }
        Money rentPrice = walletService.payForRent(rentPaymentCommand);
        reportService.save(
                RentalNumber.of(rentPaymentCommand.rentalNumber()),
                UserId.of(rentPaymentCommand.userId()),
                rentPrice
        );
    }

    // This is probably only for testing ...
    public void depositToWallet(WalletDepositCommand walletDepositCommand) {
//        User user = authenticationService.getLoggedInUser();
//        // TODO: Change filter to only allow this for admin
//        if(user.role() != UserRole.ADMIN && !Objects.equals(walletDepositCommand.userId(), user.id())) {
//            log.warning(prefix() + "Only Admin can deposit to wallet");
//            throw new MethodNotAllowedException();
//        }
        log.warning(prefix() + "Depositing...");
        walletService.deposit(walletDepositCommand);
    }

    private String prefix() {
        return "[PaymentService] ";
    }
}
