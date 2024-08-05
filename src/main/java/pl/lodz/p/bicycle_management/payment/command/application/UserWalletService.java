package pl.lodz.p.bicycle_management.payment.command.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lodz.p.bicycle_management.payment.command.domain.*;

@Service
@Transactional
@RequiredArgsConstructor
@Log
public class UserWalletService { // TODO: This will replace PaymentService?
    private final UserWalletRepository userWalletRepository;

    public UserWallet create(final CreateCommand createCommand) {
        return userWalletRepository.save(
                UserWalletFactory.createUserWallet(
                        UserId.of(createCommand.userId())
                )
        );
    }

    public void remove(final RemoveCommand removeCommand) {
        userWalletRepository.removeByUserId(UserId.of(removeCommand.userId()));
    }
}
