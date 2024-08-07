package pl.lodz.p.bicycle_management.payment.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.bicycle_management.payment.command.application.AuthenticationService;
import pl.lodz.p.bicycle_management.payment.command.application.WalletDepositCommand;
import pl.lodz.p.bicycle_management.payment.command.application.WalletService;
import pl.lodz.p.bicycle_management.payment.command.domain.MethodNotAllowedException;
import pl.lodz.p.bicycle_management.payment.command.domain.User;
import pl.lodz.p.bicycle_management.payment.query.facade.PageUserWalletDto;
import pl.lodz.p.bicycle_management.payment.query.facade.UserWalletDto;
import pl.lodz.p.bicycle_management.payment.query.facade.UserWalletFacade;
import pl.lodz.p.bicycle_management.user.domain.UserService;

import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/wallet",
        produces = "application/json",
        consumes = "application/json"
)
public class WalletController {

    private final AuthenticationService authenticationService;
    private final UserWalletFacade userWalletFacade;

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserWalletDto> getWallet(@PathVariable Integer userId) {
        return ResponseEntity.ok(userWalletFacade.findByUserId(userId));
    }

    @GetMapping(path = "/my")
    public ResponseEntity<UserWalletDto> getMyWallet()
    {
        User user = authenticationService.getLoggedInUser();
        return ResponseEntity.ok(userWalletFacade.findByUserId(user.id()));
    }

    @GetMapping
    public ResponseEntity<PageUserWalletDto> getAllWallets(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(userWalletFacade.findAll(pageable));
    }
}
