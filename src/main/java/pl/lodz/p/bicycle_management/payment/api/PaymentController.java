package pl.lodz.p.bicycle_management.payment.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.p.bicycle_management.payment.command.application.AuthenticationService;
import pl.lodz.p.bicycle_management.payment.command.application.PaymentService;
import pl.lodz.p.bicycle_management.payment.command.application.WalletDepositCommand;
import pl.lodz.p.bicycle_management.payment.command.domain.MethodNotAllowedException;
import pl.lodz.p.bicycle_management.payment.command.domain.User;

import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/payment",
        produces = "application/json",
        consumes = "application/json"
)
public class PaymentController {
    private final PaymentService paymentService;

    // This is probably only for testing ...
    @PostMapping(path = "/deposit")
    public ResponseEntity<Void> depositToWallet(@RequestBody WalletDepositCommand walletDepositCommand) {
        paymentService.depositToWallet(walletDepositCommand);
        return ResponseEntity.ok().build();
    }
}
