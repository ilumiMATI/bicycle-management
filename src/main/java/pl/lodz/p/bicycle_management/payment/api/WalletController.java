package pl.lodz.p.bicycle_management.payment.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.bicycle_management.payment.command.application.AuthenticationService;
import pl.lodz.p.bicycle_management.payment.command.domain.User;
import pl.lodz.p.bicycle_management.payment.query.facade.UserWalletDto;
import pl.lodz.p.bicycle_management.payment.query.facade.UserWalletFacade;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/wallet",
        produces = "application/json",
        consumes = "application/json"
)
public class WalletController {

    private final UserWalletFacade userWalletFacade;
    private final AuthenticationService authenticationService;

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserWalletDto> getWallet(@PathVariable Integer userId) {
        return ResponseEntity.ok(userWalletFacade.findByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<UserWalletDto> getMyWallet()
    {
        User user = authenticationService.getLoggedInUser();
        return ResponseEntity.ok(userWalletFacade.findByUserId(user.id()));
    }
}
